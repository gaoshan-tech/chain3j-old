package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray30<T extends Type> extends StaticArray<T> {
    public StaticArray30(List<T> values) {
        super(30, values);
    }

    @SafeVarargs
    public StaticArray30(T... values) {
        super(30, values);
    }
}
