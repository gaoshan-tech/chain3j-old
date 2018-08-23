package org.chain3j.protocol.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.chain3j.protocol.Chain3j;
import org.chain3j.protocol.core.methods.response.McAccounts;
import org.chain3j.protocol.core.methods.response.McBlock;
import org.chain3j.protocol.core.methods.response.McBlockNumber;
import org.chain3j.protocol.core.methods.response.McCall;
import org.chain3j.protocol.core.methods.response.McCoinbase;
import org.chain3j.protocol.core.methods.response.McCompileLLL;
import org.chain3j.protocol.core.methods.response.McCompileSerpent;
import org.chain3j.protocol.core.methods.response.McCompileSolidity;
import org.chain3j.protocol.core.methods.response.McEstimateGas;
import org.chain3j.protocol.core.methods.response.McFilter;
import org.chain3j.protocol.core.methods.response.McGasPrice;
import org.chain3j.protocol.core.methods.response.McGetBalance;
import org.chain3j.protocol.core.methods.response.McGetBlockTransactionCountByHash;
import org.chain3j.protocol.core.methods.response.McGetBlockTransactionCountByNumber;
import org.chain3j.protocol.core.methods.response.McGetCode;
import org.chain3j.protocol.core.methods.response.McGetCompilers;
import org.chain3j.protocol.core.methods.response.McGetStorageAt;
import org.chain3j.protocol.core.methods.response.McGetTransactionCount;
import org.chain3j.protocol.core.methods.response.McGetTransactionReceipt;
import org.chain3j.protocol.core.methods.response.McGetUncleCountByBlockHash;
import org.chain3j.protocol.core.methods.response.McGetUncleCountByBlockNumber;
import org.chain3j.protocol.core.methods.response.McGetWork;
import org.chain3j.protocol.core.methods.response.McHashrate;
import org.chain3j.protocol.core.methods.response.McLog;
import org.chain3j.protocol.core.methods.response.McMining;
import org.chain3j.protocol.core.methods.response.McProtocolVersion;
import org.chain3j.protocol.core.methods.response.McSendTransaction;
import org.chain3j.protocol.core.methods.response.McSign;
import org.chain3j.protocol.core.methods.response.McSyncing;
import org.chain3j.protocol.core.methods.response.McTransaction;
import org.chain3j.protocol.core.methods.response.McUninstallFilter;
import org.chain3j.protocol.core.methods.response.NetListening;
import org.chain3j.protocol.core.methods.response.NetPeerCount;
import org.chain3j.protocol.core.methods.response.NetVersion;
import org.chain3j.protocol.core.methods.response.Transaction;
import org.chain3j.protocol.core.methods.response.TransactionReceipt;
import org.chain3j.protocol.core.methods.response.Chain3ClientVersion;
import org.chain3j.protocol.core.methods.response.Chain3Sha3;
import org.chain3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JSON-RPC 2.0 Integration Tests.
 */
public class CoreIT {

    private Chain3j chain3j;

    private IntegrationTestConfig config = new MoacTestnetConfig();

    public CoreIT() {
    }

    @Before
    public void setUp() {
        this.chain3j = Chain3j.build(new HttpService());
    }

    //客户端版本
    @Test
    public void testChain3ClientVersion() throws Exception {
        Chain3ClientVersion chain3ClientVersion = chain3j.chain3ClientVersion().send();
        String clientVersion = chain3ClientVersion.getChainClientVersion();
        System.out.println("moac client version: " + clientVersion);
        assertFalse(clientVersion.isEmpty());
    }

    //返回指定数据的Keccak-256（不同于标准的SHA3-256算法）哈希值。
    @Test
    public void testChain3Sha3() throws Exception {
        Chain3Sha3 chain3Sha3 = chain3j.chain3Sha3("0x68656c6c6f20776f726c64").send();
        assertThat(chain3Sha3.getResult(),
                is("0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad"));
    }

    //返回当前连接网络的ID
    @Test
    public void testNetVersion() throws Exception {
        NetVersion netVersion = chain3j.netVersion().send();
        assertFalse(netVersion.getNetVersion().isEmpty());
    }

    //如果客户端处于监听网络连接的状态，该调用返回true。
    @Test
    public void testNetListening() throws Exception {
        NetListening netListening = chain3j.netListening().send();
        assertTrue(netListening.isListening());
    }

    //连接的节点数
    @Test
    public void testNetPeerCount() throws Exception {
        NetPeerCount netPeerCount = chain3j.netPeerCount().send();
        assertTrue(netPeerCount.getQuantity().signum() == 1);
    }

    //协议版本
    @Test
    public void testMcProtocolVersion() throws Exception {
        McProtocolVersion ethProtocolVersion = chain3j.mcProtocolVersion().send();
        assertFalse(ethProtocolVersion.getProtocolVersion().isEmpty());
    }

    //是否在同步区块
    @Test
    public void testMcSyncing() throws Exception {
        McSyncing mcSyncing = chain3j.mcSyncing().send();
        assertNotNull(mcSyncing.getResult());
    }

    //挖矿奖励账户
    @Test
    public void testMcCoinbase() throws Exception {
        McCoinbase ethCoinbase = chain3j.mcCoinbase().send();
        assertNotNull(ethCoinbase.getAddress());
    }

    //是否在挖矿
    @Test
    public void testMcMining() throws Exception {
        McMining ethMining = chain3j.mcMining().send();
        assertNotNull(ethMining.getResult());
    }

    //挖矿速度
    @Test
    public void testMcHashrate() throws Exception {
        McHashrate ethHashrate = chain3j.mcHashrate().send();
        assertThat(ethHashrate.getHashrate(), is(BigInteger.ZERO));
    }

    //当前gas price
    @Test
    public void testMcGasPrice() throws Exception {
        McGasPrice ethGasPrice = chain3j.mcGasPrice().send();
        assertTrue(ethGasPrice.getGasPrice().signum() == 1);
    }

    //节点账户列表
    @Test
    public void testMcAccounts() throws Exception {
        McAccounts ethAccounts = chain3j.mcAccounts().send();
        assertNotNull(ethAccounts.getAccounts());
    }

    //区块数量
    @Test
    public void testMcBlockNumber() throws Exception {
        McBlockNumber ethBlockNumber = chain3j.mcBlockNumber().send();
        assertTrue(ethBlockNumber.getBlockNumber().signum() == 1);
    }

    //获取余额
    @Test
    public void testMcGetBalance() throws Exception {
        McGetBalance ethGetBalance = chain3j.mcGetBalance(
                config.validAccount(), DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(ethGetBalance.getBalance().signum() == 1);
    }

    //返回账号存储位置的值。
    @Test
    public void testMcGetStorageAt() throws Exception {
        McGetStorageAt ethGetStorageAt = chain3j.mcGetStorageAt(
                config.validContractAddress(),
                BigInteger.valueOf(0),
                DefaultBlockParameter.valueOf("latest")).send();
        assertThat(ethGetStorageAt.getData(), is(config.validContractAddressPositionZero()));
    }

    //获取账号交易次数   (该次数用nonce参数设置)
    @Test
    public void testMcGetTransactionCount() throws Exception {
        McGetTransactionCount ethGetTransactionCount = chain3j.mcGetTransactionCount(
                config.validAccount(),
                DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(ethGetTransactionCount.getTransactionCount().signum() == 1);
    }

    //返回指定块内的交易数量，使用哈希来指定块。
    @Test
    public void testMcGetBlockTransactionCountByHash() throws Exception {
        McGetBlockTransactionCountByHash ethGetBlockTransactionCountByHash =
                chain3j.mcGetBlockTransactionCountByHash(
                        config.validBlockHash()).send();
        assertThat(ethGetBlockTransactionCountByHash.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    //返回指定块内的交易数量，使用块序号。
    @Test
    public void testMcGetBlockTransactionCountByNumber() throws Exception {
        McGetBlockTransactionCountByNumber ethGetBlockTransactionCountByNumber =
                chain3j.mcGetBlockTransactionCountByNumber(
                        DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(ethGetBlockTransactionCountByNumber.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    //返回指定块 叔块，使用哈希来指定块。
    @Test
    public void testMcGetUncleCountByBlockHash() throws Exception {
        McGetUncleCountByBlockHash ethGetUncleCountByBlockHash =
                chain3j.mcGetUncleCountByBlockHash(config.validBlockHash()).send();
        assertThat(ethGetUncleCountByBlockHash.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    //返回指定块 叔块，使用序号来指定块。
    @Test
    public void testMcGetUncleCountByBlockNumber() throws Exception {
        McGetUncleCountByBlockNumber ethGetUncleCountByBlockNumber =
                chain3j.mcGetUncleCountByBlockNumber(
                        DefaultBlockParameter.valueOf("latest")).send();
        assertThat(ethGetUncleCountByBlockNumber.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    //返回指定地址的代码。
    @Test
    public void testMcGetCode() throws Exception {
        McGetCode ethGetCode = chain3j.mcGetCode(config.validContractAddress(),
                DefaultBlockParameter.valueOf("latest")).send();
        assertThat(ethGetCode.getCode(), is(config.validContractCode()));
    }

    //    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testMcSign() throws Exception {
        McSign ethSign = chain3j.mcSign(config.validAccount(), "0xdeadbeaf").send();
        assertThat(ethSign.getSignature(),
                equalTo("0x38146338c44eb128cdbbdace2c9cad3094ad49aceb8c83659247e4e24b56907e09715a61942ca1de4e2d9e2804dd8683aec356b2c0702f120d8c9ee7acad99411c"));
    }

    //发送一个普通交易
//    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testMcSendTransaction() throws Exception {
        McSendTransaction ethSendTransaction = chain3j.mcSendTransaction(
                config.buildTransaction()).send();
        assertFalse(ethSendTransaction.getTransactionHash().isEmpty());
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testMcSendRawTransaction() throws Exception {

    }

    @Test
    public void testMcCall() throws Exception {
        McCall ethCall = chain3j.mcCall(config.buildTransaction(),
                DefaultBlockParameter.valueOf("latest")).send();

        assertThat(DefaultBlockParameterName.LATEST.getValue(), is("latest"));
        assertThat(ethCall.getValue(), is("0x"));
    }

    // 获取普通交易的gas上限
    @Test
    public void testMcEstimateGas() throws Exception {
        McEstimateGas ethEstimateGas = chain3j.mcEstimateGas(config.buildTransaction())
                .send();
        assertTrue(ethEstimateGas.getAmountUsed().signum() == 1);
    }

    //返回指快哈希的块信息。
    @Test
    public void testMcGetBlockByHashReturnHashObjects() throws Exception {
        McBlock ethBlock = chain3j.mcGetBlockByHash(config.validBlockHash(), false)
                .send();

        McBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                is(config.validBlockTransactionCount().intValue()));
    }

    //返回指快哈希的交易信息。
    @Test
    public void testMcGetBlockByHashReturnFullTransactionObjects() throws Exception {
        McBlock ethBlock = chain3j.mcGetBlockByHash(config.validBlockHash(), true)
                .send();

        McBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    //返回指定编号的块信息。
    @Test
    public void testMcGetBlockByNumberReturnHashObjects() throws Exception {
        McBlock ethBlock = chain3j.mcGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), false).send();

        McBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    //非api接口，用来获取一个有交易的快序号
    @Test
    public void testMcGetBlockHasTransaction() {
        for (int i = 44450; i < 100000; i++) {
            try {
                McBlock ethBlock = chain3j.mcGetBlockByNumber(
                        DefaultBlockParameter.valueOf(BigInteger.valueOf(i)), false).send();

                McBlock.Block block = ethBlock.getBlock();
                if (block.getTransactions().size() != 0) {
                    System.out.print(block.getTransactions().size() + "------------------------------   " + block.getNumber() + "---------------------------");
                    break;
                }
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //返回指定编号的块信息---交易信息
    @Test
    public void testMcGetBlockByNumberReturnTransactionObjects() throws Exception {
        McBlock ethBlock = chain3j.mcGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), true).send();

        McBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    //返回指定交易哈希的交易信息
    @Test
    public void testMcGetTransactionByHash() throws Exception {
        McTransaction ethTransaction = chain3j.mcGetTransactionByHash(
                config.validTransactionHash()).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
    }

    //返回指定制定快哈希的第INdex的交易信息
    @Test
    public void testMcGetTransactionByBlockHashAndIndex() throws Exception {
        BigInteger index = BigInteger.ZERO;

        McTransaction ethTransaction = chain3j.mcGetTransactionByBlockHashAndIndex(
                config.validBlockHash(), index).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    //返回指定制定快地址的第INdex的交易信息
    @Test
    public void testMcGetTransactionByBlockNumberAndIndex() throws Exception {
        BigInteger index = BigInteger.ZERO;

        McTransaction ethTransaction = chain3j.mcGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validBlock()), index).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    //返回指定交易的收据，使用哈希指定交易。
    @Test
    public void testMcGetTransactionReceipt() throws Exception {
        McGetTransactionReceipt ethGetTransactionReceipt = chain3j.mcGetTransactionReceipt(
                config.validTransactionHash()).send();
        assertTrue(ethGetTransactionReceipt.getTransactionReceipt().isPresent());
        TransactionReceipt transactionReceipt =
                ethGetTransactionReceipt.getTransactionReceipt().get();
        assertThat(transactionReceipt.getTransactionHash(), is(config.validTransactionHash()));
    }

    //返回具有指定哈希的块具有指定索引位置的叔伯。
    @Test
    public void testMcGetUncleByBlockHashAndIndex() throws Exception {
        McBlock ethBlock = chain3j.mcGetUncleByBlockHashAndIndex(
                config.validUncleBlockHash(), BigInteger.ZERO).send();
        assertNotNull(ethBlock.getBlock());
    }

    //返回具有指定编号的块内具有指定索引序号的叔伯。
    @Test
    public void testMcGetUncleByBlockNumberAndIndex() throws Exception {
        McBlock ethBlock = chain3j.mcGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validUncleBlock()), BigInteger.ZERO)
                .send();
        assertNotNull(ethBlock.getBlock());
    }

    //返回客户端中有效的编译器列表。(弃用接口)
    @Test
    public void testMcGetCompilers() throws Exception {
        McGetCompilers ethGetCompilers = chain3j.mcGetCompilers().send();
        assertNotNull(ethGetCompilers.getCompilers());
    }

    @Ignore  // The method mc_compileLLL does not exist/is not available
    @Test
    public void testMcCompileLLL() throws Exception {
        McCompileLLL ethCompileLLL = chain3j.mcCompileLLL(
                "(returnlll (suicide (caller)))").send();
        assertFalse(ethCompileLLL.getCompiledSourceCode().isEmpty());
    }

    @Ignore  // The method mc_compileSerpent does not exist/is not available
    @Test
    public void testMcCompileSolidity() throws Exception {
        String sourceCode = "pragma solidity ^0.4.0;"
                + "\ncontract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }"
                + "\ncontract test2 { function multiply2(uint a) returns(uint d) {"
                + "   return a * 7;   } }";
        McCompileSolidity ethCompileSolidity = chain3j.mcCompileSolidity(sourceCode)
                .send();
        assertNotNull(ethCompileSolidity.getCompiledSolidity());
        assertThat(
                ethCompileSolidity.getCompiledSolidity().get("test2").getInfo().getSource(),
                is(sourceCode));
    }

    @Ignore  // The method mc_compileSerpent does not exist/is not available
    @Test
    public void testMcCompileSerpent() throws Exception {
        McCompileSerpent ethCompileSerpent = chain3j.mcCompileSerpent(
                "/* some serpent */").send();
        assertFalse(ethCompileSerpent.getCompiledSourceCode().isEmpty());
    }

    //订制过滤器相关测试
    @Test
    public void testFiltersByFilterId() throws Exception {
        org.chain3j.protocol.core.methods.request.McFilter mcFilter =
                new org.chain3j.protocol.core.methods.request.McFilter(
                        DefaultBlockParameterName.EARLIEST,
                        DefaultBlockParameterName.LATEST,
                        config.validContractAddress());

        String eventSignature = config.encodedEvent();
        mcFilter.addSingleTopic(eventSignature);

        // mc_newFilter
        McFilter ethNewFilter = chain3j.mcNewFilter(mcFilter).send();
        BigInteger filterId = ethNewFilter.getFilterId();

        // mc_getFilterLogs
        McLog mcFilterLogs = chain3j.mcGetFilterLogs(filterId).send();
        List<McLog.LogResult> filterLogs = mcFilterLogs.getLogs();
//        assertFalse(filterLogs.isEmpty());

        // mc_getFilterChanges - nothing will have changed in this interval
        McLog ethLog = chain3j.mcGetFilterChanges(filterId).send();
//        assertTrue(ethLog.getLogs().isEmpty());

        // mc_uninstallFilter
        McUninstallFilter ethUninstallFilter = chain3j.mcUninstallFilter(filterId).send();
        assertTrue(ethUninstallFilter.isUninstalled());
    }

    //在节点中创建一个过滤器，以便当新块生成时进行通知
    @Test
    public void testMcNewBlockFilter() throws Exception {
        McFilter ethNewBlockFilter = chain3j.mcNewBlockFilter().send();
        assertNotNull(ethNewBlockFilter.getFilterId());
    }

    //在节点中创建一个过滤器，以便当产生挂起交易时进行通知
    @Test
    public void testMcNewPendingTransactionFilter() throws Exception {
        McFilter ethNewPendingTransactionFilter =
                chain3j.mcNewPendingTransactionFilter().send();
        assertNotNull(ethNewPendingTransactionFilter.getFilterId());
    }

    //返回指定过滤器中的所有日志。
    @Test
    public void testMcGetLogs() throws Exception {
        org.chain3j.protocol.core.methods.request.McFilter mcFilter =
                new org.chain3j.protocol.core.methods.request.McFilter(
                        DefaultBlockParameterName.EARLIEST,
                        DefaultBlockParameterName.LATEST,
                        config.validContractAddress()
                );

        mcFilter.addSingleTopic(config.encodedEvent());

        McLog ethLog = chain3j.mcGetLogs(mcFilter).send();
        List<McLog.LogResult> logs = ethLog.getLogs();
//        assertFalse(logs.isEmpty());
    }

    //返回当前块的哈希、种子哈希、以及要满足的边界条件
    @Test
    public void testMcGetWork() throws Exception {
        McGetWork ethGetWork = chain3j.mcGetWork().send();
        assertNotNull(ethGetWork.getResult());
    }

    //用于提交POW解决方案。
    @Test
    public void testMcSubmitWork() throws Exception {
        chain3j.mcSubmitWork("0x0000000000000001",
                "0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
                "0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000").send();

    }

    //用于提交挖矿的哈希速率。
//    @Test
//    public void testMcSubmitHashrate() throws Exception {
//        chain3j.mcSubmitHashrate(
//                "0x0000000000000000000000000000000000000000000000000000000000500000",
//                "0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c").send();
//
//    }
//
//    //在本地数据库中存入字符串。
//    @Ignore  // The method  does not exist/is not available
//    @Test
//    public void testDbPutString() throws Exception {
//        chain3j.dbPutString("testDB", "myKey", "myString").send();
//
//    }
//
//    //从本地数据库读取字符串。
//    @Ignore  // The method  does not exist/is not available
//    @Test
//    public void testDbGetString() throws Exception {
//        chain3j.dbGetString("testDB", "myKey").send();
//    }
//
//    //将二进制数据写入本地数据库。
//    @Ignore  // The method  does not exist/is not available
//    @Test
//    public void testDbPutHex() throws Exception {
//        chain3j.dbPutHex("testDB", "myKey", "0x68656c6c6f20776f726c64").send();
//
//    }
//
//    //从本地数据库中读取二进制数据。
//    @Ignore  // The method  does not exist/is not available
//    @Test
//    public void testDbGetHex() throws Exception {
//        chain3j.dbGetHex("testDB", "myKey").send();
//    }
//
//    @Test
//    public void testShhPost() throws Exception {
//        chain3j.shhPost(new ShhPost(
//                "0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1",
//                "0x3e245533f97284d442460f2998cd41858798ddf04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a0d4d661997d3940272b717b1",
//                Arrays.asList("0x776869737065722d636861742d636c69656e74", "0x4d5a695276454c39425154466b61693532"),
//                "0x7b2274797065223a226d6",
//                Numeric.toBigInt("0x64"),
//                Numeric.toBigInt("0x64"))).send();
//    }
//
//    @Ignore // The method shh_version does not exist/is not available
//    @Test
//    public void testShhVersion() throws Exception {
//        ShhVersion shhVersion = chain3j.shhVersion().send();
//        assertNotNull(shhVersion.getVersion());
//    }
//
//    @Ignore  // The method shh_newIdentity does not exist/is not available
//    @Test
//    public void testShhNewIdentity() throws Exception {
//        ShhNewIdentity shhNewIdentity = chain3j.shhNewIdentity().send();
//        assertNotNull(shhNewIdentity.getAddress());
//    }
//
//    @Test
//    public void testShhHasIdentity() throws Exception {
//
//    }
//
//    @Ignore  // The method shh_newIdentity does not exist/is not available
//    @Test
//    public void testShhNewGroup() throws Exception {
//        ShhNewGroup shhNewGroup = chain3j.shhNewGroup().send();
//        assertNotNull(shhNewGroup.getAddress());
//    }
//
//    @Ignore  // The method shh_addToGroup does not exist/is not available
//    @Test
//    public void testShhAddToGroup() throws Exception {
//
//    }
//
//    @Test
//    public void testShhNewFilter() throws Exception {
//        chain3j.shhNewFilter(
//                new ShhFilter("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1")
//                        .addSingleTopic("0x12341234bf4b564f")).send();
//    }
//
//    @Test
//    public void testShhUninstallFilter() throws Exception {
//
//    }
//
//    @Test
//    public void testShhGetFilterChanges() throws Exception {
//
//    }
//
//    @Test
//    public void testShhGetMessages() throws Exception {
//
//    }
}
