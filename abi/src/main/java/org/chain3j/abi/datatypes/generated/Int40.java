package org.chain3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.chain3j.abi.datatypes.Int;

/**
 * Auto generated code.
 */
public class Int40 extends Int {
    public static final Int40 DEFAULT = new Int40(BigInteger.ZERO);

    public Int40(BigInteger value) {
        super(40, value);
    }

    public Int40(long value) {
        this(BigInteger.valueOf(value));
    }
}
