package org.chain3j.protocol.parity.methods.response;

import org.chain3j.protocol.core.Response;

/**
 * trace_get.
 */
public class ParityTraceGet extends Response<Trace> {
    public Trace getTrace() {
        return getResult();
    }
}
