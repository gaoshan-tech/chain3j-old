package org.chain3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.chain3j.abi.datatypes.Int;

/**
 * Auto generated code.
 */
public class Int32 extends Int {
    public static final Int32 DEFAULT = new Int32(BigInteger.ZERO);

    public Int32(BigInteger value) {
        super(32, value);
    }

    public Int32(long value) {
        this(BigInteger.valueOf(value));
    }
}
