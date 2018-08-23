package org.chain3j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.chain3j.protocol.Chain3j;

import org.chain3j.protocol.core.Request;
import org.chain3j.protocol.core.Response;
import org.chain3j.protocol.core.Response.Error;
import org.chain3j.protocol.core.RpcErrors;
import org.chain3j.protocol.core.methods.response.McFilter;
import org.chain3j.protocol.core.methods.response.McLog;
import org.chain3j.protocol.core.methods.response.McUninstallFilter;


/**
 * Class for creating managed filter requests with callbacks.
 */
public abstract class Filter<T> {

    private static final Logger log = LoggerFactory.getLogger(Filter.class);

    final Chain3j chain3j;
    final Callback<T> callback;

    private volatile BigInteger filterId;

    private ScheduledFuture<?> schedule;
    
    private ScheduledExecutorService scheduledExecutorService;

    private long blockTime;

    public Filter(Chain3j chain3j, Callback<T> callback) {
        this.chain3j = chain3j;
        this.callback = callback;
    }

    public void run(ScheduledExecutorService scheduledExecutorService, long blockTime) {
        try {
            McFilter mcFilter = sendRequest();
            if (mcFilter.hasError()) {
                throwException(mcFilter.getError());
            }

            filterId = mcFilter.getFilterId();
            this.scheduledExecutorService = scheduledExecutorService;
            this.blockTime = blockTime;
            getInitialFilterLogs();

            schedule = scheduledExecutorService.scheduleAtFixedRate(
                    () -> {
                        try {
                            this.pollFilter(mcFilter);
                        } catch (Throwable e) {
                            // All exceptions must be caught, otherwise our job terminates without
                            // any notification
                            log.error("Error sending request", e);
                        }
                    },
                    0, blockTime, TimeUnit.MILLISECONDS);
        } catch (IOException e) {
            throwException(e);
        }
    }

    private void getInitialFilterLogs() {
        try {
            Optional<Request<?, McLog>> maybeRequest = this.getFilterLogs(this.filterId);
            McLog ethLog = null;
            if (maybeRequest.isPresent()) {
                ethLog = maybeRequest.get().send();
            } else {
                ethLog = new McLog();
                ethLog.setResult(Collections.emptyList());
            }
            process(ethLog.getLogs());

        } catch (IOException e) {
            throwException(e);
        }
    }

    private void pollFilter(McFilter mcFilter) {
        McLog ethLog = null;
        try {
            ethLog = chain3j.mcGetFilterChanges(filterId).send();
        } catch (IOException e) {
            throwException(e);
        }
        if (ethLog.hasError()) {
            Error error = ethLog.getError();
            switch (error.getCode()) {
                case RpcErrors.FILTER_NOT_FOUND: reinstallFilter();
                    break;
                default: throwException(error);
                    break;
            }
        } else {
            process(ethLog.getLogs());
        }
    }

    abstract McFilter sendRequest() throws IOException;

    abstract void process(List<McLog.LogResult> logResults);
    
    private void reinstallFilter() {
        log.warn("The filter has not been found. Filter id: " + filterId);
        schedule.cancel(true);
        this.run(scheduledExecutorService, blockTime);
    }

    public void cancel() {
        schedule.cancel(false);

        try {
            McUninstallFilter ethUninstallFilter = chain3j.mcUninstallFilter(filterId).send();
            if (ethUninstallFilter.hasError()) {
                throwException(ethUninstallFilter.getError());
            }

            if (!ethUninstallFilter.isUninstalled()) {
                throw new FilterException("Filter with id '" + filterId + "' failed to uninstall");
            }
        } catch (IOException e) {
            throwException(e);
        }
    }

    protected abstract Optional<Request<?, McLog>> getFilterLogs(BigInteger filterId);

    void throwException(Response.Error error) {
        throw new FilterException("Invalid request: "
                + (error == null ? "Unknown Error" : error.getMessage()));
    }

    void throwException(Throwable cause) {
        throw new FilterException("Error sending request", cause);
    }
}
