package org.chain3j.protocol.parity.methods.response;

import org.chain3j.protocol.core.Response;

/**
 * trace_rawTransaction
 * trace_replayTransaction.
 */
public class ParityFullTraceResponse extends Response<FullTraceInfo> {
    public FullTraceInfo getFullTraceInfo() {
        return getResult();
    }
}
