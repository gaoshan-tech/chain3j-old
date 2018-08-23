package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray2<T extends Type> extends StaticArray<T> {
    public StaticArray2(List<T> values) {
        super(2, values);
    }

    @SafeVarargs
    public StaticArray2(T... values) {
        super(2, values);
    }
}
