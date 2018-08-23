package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray10<T extends Type> extends StaticArray<T> {
    public StaticArray10(List<T> values) {
        super(10, values);
    }

    @SafeVarargs
    public StaticArray10(T... values) {
        super(10, values);
    }
}
