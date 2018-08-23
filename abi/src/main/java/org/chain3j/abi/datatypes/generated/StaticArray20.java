package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray20<T extends Type> extends StaticArray<T> {
    public StaticArray20(List<T> values) {
        super(20, values);
    }

    @SafeVarargs
    public StaticArray20(T... values) {
        super(20, values);
    }
}
