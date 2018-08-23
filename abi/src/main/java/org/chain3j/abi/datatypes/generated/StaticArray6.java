package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray6<T extends Type> extends StaticArray<T> {
    public StaticArray6(List<T> values) {
        super(6, values);
    }

    @SafeVarargs
    public StaticArray6(T... values) {
        super(6, values);
    }
}
