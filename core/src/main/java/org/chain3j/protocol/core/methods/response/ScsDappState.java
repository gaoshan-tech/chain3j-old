package org.chain3j.protocol.core.methods.response;

import org.chain3j.protocol.core.Response;
import org.chain3j.utils.Numeric;

import java.math.BigInteger;

public class ScsDappState extends Response<String> {
    public BigInteger getDappState() {
        return Numeric.decodeQuantity(getResult());
    }
}
