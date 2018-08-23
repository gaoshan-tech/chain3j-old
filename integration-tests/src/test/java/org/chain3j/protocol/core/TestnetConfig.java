package org.chain3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;

import org.chain3j.abi.EventEncoder;
import org.chain3j.abi.TypeReference;
import org.chain3j.abi.datatypes.Event;
import org.chain3j.abi.datatypes.Uint;
import org.chain3j.protocol.core.methods.request.Transaction;

/**
 * Mordon Testnet Configuration.
 */
public class TestnetConfig implements IntegrationTestConfig {

    @Override
    public String validBlockHash() {
        https://testnet.etherscan.io/block/1627453
        return "0x17da7ea6d63cb00b6752d1ec698483a5262ebdceb4dcbaca078747f231265873";
    }

    @Override
    public BigInteger validBlock() {
        // https://testnet.etherscan.io/block/71032
        return BigInteger.valueOf(695);
    }

    @Override
    public BigInteger validBlockTransactionCount() {
        return BigInteger.valueOf(1);
    }

    @Override
    public BigInteger validBlockUncleCount() {
        return BigInteger.ZERO;
    }

    @Override
    public String validAccount() {
        // https://testnet.etherscan.io/address/0xCB10FBad79F5e602699fFf2Bb4919Fbd87AbC8CC
        return "0x0B6BfE6D83f3F2f73eA1B0EDC12cE714FA52842E";
    }

    @Override
    public String validContractAddress() {
        // Deployed fibonacci example
        return "0x1d703E79dF534b0d98b1e6fe6e9b156224f3Befd";
    }

    @Override
    public String validContractAddressPositionZero() {
        return "0x0000000000000000000000000000000000000000000000000000000000000000";
    }

    @Override
    public String validContractCode() {
        return "0x6080604052600436106100615763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416636392a51f81146100665780638da5cb5b14610099578063a0712d68146100ca578063a9059cbb146100e4575b600080fd5b34801561007257600080fd5b50610087600160a060020a0360043516610108565b60408051918252519081900360200190f35b3480156100a557600080fd5b506100ae61011a565b60408051600160a060020a039092168252519081900360200190f35b3480156100d657600080fd5b506100e2600435610129565b005b3480156100f057600080fd5b506100e2600160a060020a036004351660243561014c565b60006020819052908152604090205481565b600154600160a060020a031681565b600154600160a060020a0316600090815260208190526040902080549091019055565b33600090815260208190526040902054811061016757600080fd5b600160a060020a0382166000908152602081905260409020548181011161018d57600080fd5b3360009081526020819052604080822080548490039055600160a060020a0393909316815291909120805490910190555600a165627a7a723058207c36d286f85aefe933acf87ae4f16ff6cdbdac60e664f5844e74cc88386c56880029";
    }

    @Override
    public Transaction buildTransaction() {
        return Transaction.createContractTransaction(
                validAccount(),
                BigInteger.valueOf(7),  // nonce
                Transaction.DEFAULT_GAS,
                validContractCode()
        );
    }

    @Override
    public String validTransactionHash() {
        return "0xc26e05b5dd81e935ca0225d441bb5996c3742f107c6590b52037c2e0d3e21818";
    }

    @Override
    public String validUncleBlockHash() {
        return "0x9d512dd0cad173dd3e7ec568794db03541c4a98448cc5940b695da604d118754";
    }

    @Override
    public BigInteger validUncleBlock() {
        return BigInteger.valueOf(695);
    }

    @Override
    public String encodedEvent() {
        Event event = new Event("Notify",
                Arrays.asList(
                        new TypeReference<Uint>(true) {},
                        new TypeReference<Uint>() {}));

        return EventEncoder.encode(event);
    }
}
