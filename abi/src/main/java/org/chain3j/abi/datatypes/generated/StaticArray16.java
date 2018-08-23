package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray16<T extends Type> extends StaticArray<T> {
    public StaticArray16(List<T> values) {
        super(16, values);
    }

    @SafeVarargs
    public StaticArray16(T... values) {
        super(16, values);
    }
}
