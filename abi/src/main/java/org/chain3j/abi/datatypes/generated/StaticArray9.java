package org.chain3j.abi.datatypes.generated;

import java.util.List;
import org.chain3j.abi.datatypes.StaticArray;
import org.chain3j.abi.datatypes.Type;

/**
 * Auto generated code.
 */
public class StaticArray9<T extends Type> extends StaticArray<T> {
    public StaticArray9(List<T> values) {
        super(9, values);
    }

    @SafeVarargs
    public StaticArray9(T... values) {
        super(9, values);
    }
}
