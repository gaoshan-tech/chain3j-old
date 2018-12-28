package org.chain3j.protocol.core;

import java.math.BigInteger;

import org.chain3j.abi.datatypes.Address;
import org.chain3j.protocol.core.methods.request.ShhFilter;
import org.chain3j.protocol.core.methods.response.*;

/**
 * Core moac JSON-RPC API.
 */
public interface Moac {
    Request<?, Chain3ClientVersion> chain3ClientVersion();

    Request<?, Chain3Sha3> chain3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, McProtocolVersion> mcProtocolVersion();

    Request<?, McCoinbase> mcCoinbase();

    Request<?, McSyncing> mcSyncing();

    Request<?, McMining> mcMining();

    Request<?, McHashrate> mcHashrate();

    Request<?, McGasPrice> mcGasPrice();

    Request<?, McAccounts> mcAccounts();

    Request<?, McBlockNumber> mcBlockNumber();

    Request<?, McGetBalance> mcGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, McGetStorageAt> mcGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, McGetTransactionCount> mcGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, McGetBlockTransactionCountByHash> mcGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, McGetBlockTransactionCountByNumber> mcGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, McGetUncleCountByBlockHash> mcGetUncleCountByBlockHash(String blockHash);

    Request<?, McGetUncleCountByBlockNumber> mcGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, McGetCode> mcGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, McSign> mcSign(String address, String sha3HashOfDataToSign);

    Request<?, McSendTransaction> mcSendTransaction(
            org.chain3j.protocol.core.methods.request.Transaction transaction);

    Request<?, McSendTransaction> mcSendRawTransaction(
            String signedTransactionData);

    Request<?, McCall> mcCall(
            org.chain3j.protocol.core.methods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, McEstimateGas> mcEstimateGas(
            org.chain3j.protocol.core.methods.request.Transaction transaction);

    Request<?, McBlock> mcGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, McBlock> mcGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, McTransaction> mcGetTransactionByHash(String transactionHash);

    Request<?, McTransaction> mcGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, McTransaction> mcGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, McGetTransactionReceipt> mcGetTransactionReceipt(String transactionHash);

    Request<?, McBlock> mcGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, McBlock> mcGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, McGetCompilers> mcGetCompilers();

    Request<?, McCompileLLL> mcCompileLLL(String sourceCode);

    Request<?, McCompileSolidity> mcCompileSolidity(String sourceCode);

    Request<?, McCompileSerpent> mcCompileSerpent(String sourceCode);

    Request<?, McFilter> mcNewFilter(org.chain3j.protocol.core.methods.request.McFilter mcFilter);

    Request<?, McFilter> mcNewBlockFilter();

    Request<?, McFilter> mcNewPendingTransactionFilter();

    Request<?, McUninstallFilter> mcUninstallFilter(BigInteger filterId);

    Request<?, McLog> mcGetFilterChanges(BigInteger filterId);

    Request<?, McLog> mcGetFilterLogs(BigInteger filterId);

    Request<?, McLog> mcGetLogs(org.chain3j.protocol.core.methods.request.McFilter mcFilter);

    Request<?, McGetWork> mcGetWork();

    Request<?, McSubmitWork> mcSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, McSubmitHashrate> mcSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGetHex> dbGetHex(String databaseName, String keyName);

    Request<?, org.chain3j.protocol.core.methods.response.ShhPost> shhPost(
            org.chain3j.protocol.core.methods.request.ShhPost shhPost);

    Request<?, ShhVersion> shhVersion();

    Request<?, ShhNewIdentity> shhNewIdentity();

    Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress);

    Request<?, ShhNewGroup> shhNewGroup();

    Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress);

    Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter);

    Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId);

    Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId);

    Request<?, ShhMessages> shhGetMessages(BigInteger filterId);

    Request<?, McBlock> scsGetBlockByNumber(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, McCall> scsDirectCall(org.chain3j.protocol.core.methods.request.Transaction transaction);

    Request<?, ScsBlockList> scsGetBlockList(String address, DefaultBlockParameter startBlockParameter, DefaultBlockParameter endBlockParameter);

    Request<?, McBlockNumber> scsGetBlockNumber(String address);

    Request<?, ScsDappState> scsGetDappState(String address);

    Request<?, ScsMicroChainList> scsGetMicroChainList();

    Request<?, ScsMicroChainInfo> scsGetMicroChainInfo();

    Request<?, ScsNonce> scsGetNonce(String address, String accountAddress);

    Request<?, ScsId> scsGetSCSId();

    Request<?, McGetTransactionReceipt> scsGetTransactionReceipt(String address, String transactionHash);
}
