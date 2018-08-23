package org.chain3j.protocol.parity.methods.response;

import java.util.ArrayList;

import org.chain3j.protocol.core.Response;

/**
 */
public class ParityAddressesResponse extends Response<ArrayList<String>> {
    public ArrayList<String> getAddresses() {
        return getResult();
    }
}
