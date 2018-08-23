package org.chain3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.chain3j.abi.datatypes.Int;

/**
 * Auto generated code.
 */
public class Int224 extends Int {
    public static final Int224 DEFAULT = new Int224(BigInteger.ZERO);

    public Int224(BigInteger value) {
        super(224, value);
    }

    public Int224(long value) {
        this(BigInteger.valueOf(value));
    }
}
