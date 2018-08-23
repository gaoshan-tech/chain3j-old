package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray12<T extends Type> extends StaticArray<T> {
    public StaticArray12(List<T> values) {
        super(12, values);
    }

    @SafeVarargs
    public StaticArray12(T... values) {
        super(12, values);
    }
}
