package org.chain3j.tx;

import java.io.IOException;
import java.math.BigInteger;

import org.chain3j.ens.EnsResolver;
import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.methods.response.McGasPrice;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.protocol.exceptions.TransactionException;


/**
 * Generic transaction manager.
 */
public abstract class ManagedTransaction {

    /**
     * @deprecated use ContractGasProvider
     * @see org.chain3j.tx.gas.DefaultGasProvider
     */
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);

    protected Chain3j chain3j;

    protected TransactionManager transactionManager;

    protected EnsResolver ensResolver;

    protected ManagedTransaction(Chain3j chain3j, TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.chain3j = chain3j;
        this.ensResolver = new EnsResolver(chain3j);
    }

    public long getSyncThreshold() {
        return ensResolver.getSyncThreshold();
    }

    public void setSyncThreshold(long syncThreshold) {
        ensResolver.setSyncThreshold(syncThreshold);
    }

    public BigInteger requestCurrentGasPrice() throws IOException {
        McGasPrice ethGasPrice = chain3j.mcGasPrice().send();

        return ethGasPrice.getGasPrice();
    }

    protected TransactionReceipt send(
            String to, String data, BigInteger value, BigInteger gasPrice, BigInteger gasLimit, BigInteger shardingFlag, String via)
            throws IOException, TransactionException {

        return transactionManager.executeTransaction(
                gasPrice, gasLimit, to, data, value, shardingFlag, via);
    }

    protected TransactionReceipt send(
            String to, String data, BigInteger value, BigInteger gasPrice, BigInteger gasLimit)
            throws IOException, TransactionException {

        return transactionManager.executeTransaction(
                gasPrice, gasLimit, to, data, value, BigInteger.ZERO, null);
    }
}
