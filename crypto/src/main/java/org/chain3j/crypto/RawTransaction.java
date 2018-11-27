package org.chain3j.crypto;

import java.math.BigInteger;

import org.chain3j.utils.Numeric;

/**
 */
public class RawTransaction {

    private BigInteger nonce;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private String to;
    private BigInteger value;
    private String data;
    private BigInteger shardingFlag;
    private String via;

    protected RawTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
                           BigInteger value, String data, BigInteger shardingFlag, String via) {
        this.nonce = nonce;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.to = to;
        this.value = value;
        this.shardingFlag = shardingFlag;
        this.via = via;

        if (data != null) {
            this.data = Numeric.cleanHexPrefix(data);
        }
    }

    public static RawTransaction createContractTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, BigInteger value,
            String init, BigInteger shardingFlag, String via) {

        return new RawTransaction(nonce, gasPrice, gasLimit, "", value, init, shardingFlag, via);
    }

    public static RawTransaction createMcTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
            BigInteger value, BigInteger shardingFlag, String via) {

        return new RawTransaction(nonce, gasPrice, gasLimit, to, value, "", shardingFlag, via);

    }

    public static RawTransaction createTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, String data, BigInteger shardingFlag, String via) {
        return createTransaction(nonce, gasPrice, gasLimit, to, BigInteger.ZERO, data, shardingFlag, via);
    }

    public static RawTransaction createTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
            BigInteger value, String data, BigInteger shardingFlag, String via) {

        return new RawTransaction(nonce, gasPrice, gasLimit, to, value, data, shardingFlag, via);
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getData() {
        return data;
    }

    public BigInteger getShardingFlag() {
        return shardingFlag;
    }

    public void setShardingFlag(BigInteger shardingFlag) {
        this.shardingFlag = shardingFlag;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
}
