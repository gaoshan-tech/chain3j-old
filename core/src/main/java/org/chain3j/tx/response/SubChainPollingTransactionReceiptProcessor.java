package org.chain3j.tx.response;

import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.methods.response.McGetTransactionReceipt;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.util.Optional;

/**
 * With each provided transaction hash, poll until we obtain a transaction receipt.
 */
public class SubChainPollingTransactionReceiptProcessor extends TransactionReceiptProcessor {

    private final String chainAddress;
    private final long sleepDuration;
    private final int attempts;
    private final Chain3j chain3j;

    public SubChainPollingTransactionReceiptProcessor(Chain3j chain3j, String chainAddress, long sleepDuration, int attempts) {
        super(chain3j);
        this.chain3j = chain3j;
        this.chainAddress = chainAddress;
        this.sleepDuration = sleepDuration;
        this.attempts = attempts;
    }

    @Override
    public TransactionReceipt waitForTransactionReceipt(
            String transactionHash)
            throws IOException, TransactionException {

        return getTransactionReceipt(transactionHash, sleepDuration, attempts);
    }

    private TransactionReceipt getTransactionReceipt(
            String transactionHash, long sleepDuration, int attempts)
            throws IOException, TransactionException {

        Optional<TransactionReceipt> receiptOptional =
                sendTransactionReceiptRequest(transactionHash);
        for (int i = 0; i < attempts; i++) {
            if (!receiptOptional.isPresent()) {
                try {
                    Thread.sleep(sleepDuration);
                } catch (InterruptedException e) {
                    throw new TransactionException(e);
                }
                receiptOptional = sendTransactionReceiptRequest(transactionHash);
            } else {
                return receiptOptional.get();
            }
        }

        throw new TransactionException("Transaction receipt was not generated after "
                + ((sleepDuration * attempts) / 1000
                + " seconds for transaction: " + transactionHash));
    }
    Optional<TransactionReceipt> sendTransactionReceiptRequest(
            String transactionHash) throws IOException, TransactionException {
        McGetTransactionReceipt transactionReceipt =
                chain3j.scsGetTransactionReceipt(chainAddress, transactionHash).send();
        if (transactionReceipt.hasError()) {
            throw new TransactionException("Error processing request: "
                    + transactionReceipt.getError().getMessage());
        }

        return transactionReceipt.getTransactionReceipt();
    }

}
