package org.chain3j.tx;

import java.io.IOException;

import org.junit.Before;

import org.chain3j.crypto.Credentials;
import org.chain3j.crypto.SampleKeys;
import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.DefaultBlockParameterName;
import org.chain3j.protocol.core.Request;
import org.chain3j.protocol.core.methods.response.McGasPrice;
import org.chain3j.protocol.core.methods.response.McGetTransactionCount;
import org.chain3j.protocol.core.methods.response.McGetTransactionReceipt;
import org.chain3j.protocol.core.methods.response.McSendTransaction;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.utils.TxHashVerifier;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public abstract class ManagedTransactionTester {

    static final String ADDRESS = "0x3d6cb163f7c72d20b0fcd6baae5889329d138a4a";
    static final String TRANSACTION_HASH = "0xHASH";
    protected Chain3j chain3j;
    protected TxHashVerifier txHashVerifier;

    @Before
    public void setUp() throws Exception {
        chain3j = mock(Chain3j.class);
        txHashVerifier = mock(TxHashVerifier.class);
        when(txHashVerifier.verify(any(), any())).thenReturn(true);
    }

    public TransactionManager getVerifiedTransactionManager(Credentials credentials,
                                                            int attempts, int sleepDuration) {
        RawTransactionManager transactionManager =
                new RawTransactionManager(chain3j, credentials, attempts, sleepDuration);
        transactionManager.setTxHashVerifier(txHashVerifier);
        return transactionManager;
    }

    public TransactionManager getVerifiedTransactionManager(Credentials credentials) {
        RawTransactionManager transactionManager = new RawTransactionManager(chain3j, credentials);
        transactionManager.setTxHashVerifier(txHashVerifier);
        return transactionManager;
    }

    void prepareTransaction(TransactionReceipt transactionReceipt) throws IOException {
        prepareNonceRequest();
        prepareTransactionRequest();
        prepareTransactionReceipt(transactionReceipt);
    }

    @SuppressWarnings("unchecked")
    void prepareNonceRequest() throws IOException {
        McGetTransactionCount ethGetTransactionCount = new McGetTransactionCount();
        ethGetTransactionCount.setResult("0x1");

        Request<?, McGetTransactionCount> transactionCountRequest = mock(Request.class);
        when(transactionCountRequest.send())
                .thenReturn(ethGetTransactionCount);
        when(chain3j.mcGetTransactionCount(SampleKeys.ADDRESS, DefaultBlockParameterName.PENDING))
                .thenReturn((Request) transactionCountRequest);
    }

    @SuppressWarnings("unchecked")
    void prepareTransactionRequest() throws IOException {
        McSendTransaction ethSendTransaction = new McSendTransaction();
        ethSendTransaction.setResult(TRANSACTION_HASH);

        Request<?, McSendTransaction> rawTransactionRequest = mock(Request.class);
        when(rawTransactionRequest.send()).thenReturn(ethSendTransaction);
        when(chain3j.mcSendRawTransaction(any(String.class)))
                .thenReturn((Request) rawTransactionRequest);
    }

    @SuppressWarnings("unchecked")
    void prepareTransactionReceipt(TransactionReceipt transactionReceipt) throws IOException {
        McGetTransactionReceipt ethGetTransactionReceipt = new McGetTransactionReceipt();
        ethGetTransactionReceipt.setResult(transactionReceipt);

        Request<?, McGetTransactionReceipt> getTransactionReceiptRequest = mock(Request.class);
        when(getTransactionReceiptRequest.send())
                .thenReturn(ethGetTransactionReceipt);
        when(chain3j.mcGetTransactionReceipt(TRANSACTION_HASH))
                .thenReturn((Request) getTransactionReceiptRequest);
    }

    @SuppressWarnings("unchecked")
    protected TransactionReceipt prepareTransfer() throws IOException {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);
        transactionReceipt.setStatus("0x1");
        prepareTransaction(transactionReceipt);

        McGasPrice ethGasPrice = new McGasPrice();
        ethGasPrice.setResult("0x1");

        Request<?, McGasPrice> gasPriceRequest = mock(Request.class);
        when(gasPriceRequest.send()).thenReturn(ethGasPrice);
        when(chain3j.mcGasPrice()).thenReturn((Request) gasPriceRequest);

        return transactionReceipt;
    }
}
