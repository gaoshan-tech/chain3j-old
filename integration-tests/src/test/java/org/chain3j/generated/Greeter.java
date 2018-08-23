package org.chain3j.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.chain3j.abi.FunctionEncoder;
import org.chain3j.abi.TypeReference;
import org.chain3j.abi.datatypes.Function;
import org.chain3j.abi.datatypes.Type;
import org.chain3j.abi.datatypes.Utf8String;
import org.chain3j.crypto.Credentials;
import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.RemoteCall;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.tx.Contract;
import org.chain3j.tx.TransactionManager;

/**
 *
 * <p>Generated with chain3j version 3.5.0.
 */
public class Greeter extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516102ec3803806102ec83398101604052805160008054600160a060020a0319163317905501805161004c906001906020840190610053565b50506100ee565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061009457805160ff19168380011785556100c1565b828001600101855582156100c1579182015b828111156100c15782518255916020019190600101906100a6565b506100cd9291506100d1565b5090565b6100eb91905b808211156100cd57600081556001016100d7565b90565b6101ef806100fd6000396000f30060806040526004361061004b5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166341c0e1b58114610050578063cfae321714610067575b600080fd5b34801561005c57600080fd5b506100656100f1565b005b34801561007357600080fd5b5061007c61012e565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100b657818101518382015260200161009e565b50505050905090810190601f1680156100e35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60005473ffffffffffffffffffffffffffffffffffffffff1633141561012c5760005473ffffffffffffffffffffffffffffffffffffffff16ff5b565b60018054604080516020601f600260001961010087891615020190951694909404938401819004810282018101909252828152606093909290918301828280156101b95780601f1061018e576101008083540402835291602001916101b9565b820191906000526020600020905b81548152906001019060200180831161019c57829003601f168201915b50505050509050905600a165627a7a72305820e425c316c8344366d073a5ea6abfa8cf7f332c73c4473364519c6caedfe04dcf0029";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_GREET = "greet";

    protected Greeter(String contractAddress, Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, chain3j, credentials, gasPrice, gasLimit);
    }

    protected Greeter(String contractAddress, Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, chain3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> greet() {
        final Function function = new Function(FUNC_GREET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<Greeter> deploy(Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.chain3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter.class, chain3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Greeter> deploy(Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.chain3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter.class, chain3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Greeter load(String contractAddress, Chain3j chain3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter(contractAddress, chain3j, credentials, gasPrice, gasLimit);
    }

    public static Greeter load(String contractAddress, Chain3j chain3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter(contractAddress, chain3j, transactionManager, gasPrice, gasLimit);
    }
}
