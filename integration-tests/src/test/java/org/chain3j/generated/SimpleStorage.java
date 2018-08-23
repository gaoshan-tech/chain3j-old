package org.chain3j.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.chain3j.abi.TypeReference;
import org.chain3j.abi.datatypes.Function;
import org.chain3j.abi.datatypes.Type;
import org.chain3j.abi.datatypes.generated.Uint256;
import org.chain3j.crypto.Credentials;
import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.RemoteCall;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.tx.Contract;
import org.chain3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.chain3j.io/command_line.html">chain3j command line tools</a>,
 * or the org.chain3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/chain3j/chain3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with chain3j version 3.5.0.
 */
public class SimpleStorage extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600560005560bf806100246000396000f30060806040526004361060485763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166360fe47b18114604d5780636d4ce63c146064575b600080fd5b348015605857600080fd5b5060626004356088565b005b348015606f57600080fd5b506076608d565b60408051918252519081900360200190f35b600055565b600054905600a165627a7a7230582043daeabaa3ce46922d0af95ff9ceb18d946da5c845e6d8d7304864b377fd8d290029";

    public static final String FUNC_SET = "set";

    public static final String FUNC_GET = "get";

    protected SimpleStorage(String contractAddress, Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, chain3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleStorage(String contractAddress, Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, chain3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> set(BigInteger x) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.chain3j.abi.datatypes.generated.Uint256(x)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<SimpleStorage> deploy(Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, chain3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SimpleStorage> deploy(Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, chain3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SimpleStorage load(String contractAddress, Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, chain3j, credentials, gasPrice, gasLimit);
    }

    public static SimpleStorage load(String contractAddress, Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, chain3j, transactionManager, gasPrice, gasLimit);
    }
}
