package org.chain3j.abi;

import java.util.List;
import java.util.stream.Collectors;

import org.chain3j.abi.datatypes.Event;
import org.chain3j.abi.datatypes.Type;
import org.chain3j.crypto.Hash;
import org.chain3j.utils.Numeric;

/**
 * <p>moac filter encoding.
 * Further limited details are available
 * </p>
 */
public class EventEncoder {

    private EventEncoder() { }

    public static String encode(Event event) {

        String methodSignature = buildMethodSignature(
                event.getName(),
                event.getParameters());

        return buildEventSignature(methodSignature);
    }

    static <T extends Type> String buildMethodSignature(
            String methodName, List<TypeReference<T>> parameters) {

        StringBuilder result = new StringBuilder();
        result.append(methodName);
        result.append("(");
        String params = parameters.stream()
                .map(p -> Utils.getTypeName(p))
                .collect(Collectors.joining(","));
        result.append(params);
        result.append(")");
        return result.toString();
    }

    public static String buildEventSignature(String methodSignature) {
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash);
    }
}
