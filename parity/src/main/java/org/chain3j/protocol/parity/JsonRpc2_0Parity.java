package org.chain3j.protocol.parity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.chain3j.crypto.WalletFile;
import org.chain3j.protocol.Chain3jService;
import org.chain3j.protocol.admin.JsonRpc2_0Admin;
import org.chain3j.protocol.admin.methods.response.BooleanResponse;
import org.chain3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.chain3j.protocol.admin.methods.response.PersonalSign;
import org.chain3j.protocol.core.DefaultBlockParameter;
import org.chain3j.protocol.core.Request;
import org.chain3j.protocol.core.methods.request.Transaction;
import org.chain3j.protocol.parity.methods.request.Derivation;
import org.chain3j.protocol.parity.methods.request.TraceFilter;
import org.chain3j.protocol.parity.methods.response.ParityAddressesResponse;
import org.chain3j.protocol.parity.methods.response.ParityAllAccountsInfo;
import org.chain3j.protocol.parity.methods.response.ParityDefaultAddressResponse;
import org.chain3j.protocol.parity.methods.response.ParityDeriveAddress;
import org.chain3j.protocol.parity.methods.response.ParityExportAccount;
import org.chain3j.protocol.parity.methods.response.ParityFullTraceResponse;
import org.chain3j.protocol.parity.methods.response.ParityListRecentDapps;
import org.chain3j.protocol.parity.methods.response.ParityTraceGet;
import org.chain3j.protocol.parity.methods.response.ParityTracesResponse;
import org.chain3j.utils.Numeric;

/**
 * JSON-RPC 2.0 factory implementation for Parity.
 */
public class JsonRpc2_0Parity extends JsonRpc2_0Admin implements Parity {

    public JsonRpc2_0Parity(Chain3jService chain3jService) {
        super(chain3jService);
    }

    @Override
    public Request<?, ParityAllAccountsInfo> parityAllAccountsInfo() {
        return new Request<>(
                "parity_allAccountsInfo",
                Collections.<String>emptyList(),
                chain3jService,
                ParityAllAccountsInfo.class);
    }

    @Override
    public Request<?, BooleanResponse> parityChangePassword(
            String accountId, String oldPassword, String newPassword) {
        return new Request<>(
                "parity_changePassword",
                Arrays.asList(accountId, oldPassword, newPassword),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, ParityDeriveAddress> parityDeriveAddressHash(
            String accountId, String password, Derivation hashType, boolean toSave) {
        return new Request<>(
                "parity_deriveAddressHash",
                Arrays.asList(accountId, password, hashType, toSave),
                chain3jService,
                ParityDeriveAddress.class);
    }

    @Override
    public Request<?, ParityDeriveAddress> parityDeriveAddressIndex(
            String accountId, String password,
            List<Derivation> indicesType, boolean toSave) {
        return new Request<>(
                "parity_deriveAddressIndex",
                Arrays.asList(accountId, password, indicesType, toSave),
                chain3jService,
                ParityDeriveAddress.class);
    }

    @Override
    public Request<?, ParityExportAccount> parityExportAccount(
            String accountId, String password) {
        return new Request<>(
                "parity_exportAccount",
                Arrays.asList(accountId, password),
                chain3jService,
                ParityExportAccount.class);
    }

    @Override
    public Request<?, ParityAddressesResponse> parityGetDappAddresses(String dAppId) {
        return new Request<>(
                "parity_getDappAddresses",
                Arrays.asList(dAppId),
                chain3jService,
                ParityAddressesResponse.class);
    }

    @Override
    public Request<?, ParityDefaultAddressResponse> parityGetDappDefaultAddress(String dAppId) {
        return new Request<>(
                "parity_getDappDefaultAddress",
                Arrays.asList(dAppId),
                chain3jService,
                ParityDefaultAddressResponse.class);
    }

    @Override
    public Request<?, ParityAddressesResponse> parityGetNewDappsAddresses() {
        return new Request<>(
                "parity_getNewDappsAddresses",
                Collections.<String>emptyList(),
                chain3jService,
                ParityAddressesResponse.class);
    }

    @Override
    public Request<?, ParityDefaultAddressResponse> parityGetNewDappsDefaultAddress() {
        return new Request<>(
                "parity_getNewDappsDefaultAddress",
                Collections.<String>emptyList(),
                chain3jService,
                ParityDefaultAddressResponse.class);
    }

    @Override
    public Request<?, ParityAddressesResponse> parityImportMoacAccounts(
            ArrayList<String> gethAddresses) {
        return new Request<>(
                "parity_importMoacAccounts",
                gethAddresses,
                chain3jService,
                ParityAddressesResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> parityKillAccount(String accountId, String password) {
        return new Request<>(
                "parity_killAccount",
                Arrays.asList(accountId, password),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, ParityAddressesResponse> parityListAccounts(
            BigInteger quantity, String accountId, DefaultBlockParameter blockParameter) {
        if (blockParameter == null) {
            return new Request<>(
                    "parity_listAccounts",
                    Arrays.asList(quantity, accountId),
                    chain3jService,
                    ParityAddressesResponse.class);
        } else {
            return new Request<>(
                    "parity_listAccounts",
                    Arrays.asList(quantity, accountId, blockParameter),
                    chain3jService,
                    ParityAddressesResponse.class);
        }
    }

    @Override
    public Request<?, ParityAddressesResponse> parityListMoacAccounts() {
        return new Request<>(
                "parity_listMoacAccounts",
                Collections.<String>emptyList(),
                chain3jService,
                ParityAddressesResponse.class);
    }

    @Override
    public Request<?, ParityListRecentDapps> parityListRecentDapps() {
        return new Request<>(
                "parity_listRecentDapps",
                Collections.<String>emptyList(),
                chain3jService,
                ParityListRecentDapps.class);
    }

    @Override
    public Request<?, NewAccountIdentifier> parityNewAccountFromPhrase(
            String phrase, String password) {
        return new Request<>(
                "parity_newAccountFromPhrase",
                Arrays.asList(phrase, password),
                chain3jService,
                NewAccountIdentifier.class);
    }

    @Override
    public Request<?, NewAccountIdentifier> parityNewAccountFromSecret(
            String secret, String password) {
        return new Request<>(
                "parity_newAccountFromSecret",
                Arrays.asList(secret, password),
                chain3jService,
                NewAccountIdentifier.class);
    }

    @Override
    public Request<?, NewAccountIdentifier> parityNewAccountFromWallet(
            WalletFile walletFile, String password) {
        return new Request<>(
                "parity_newAccountFromWallet",
                Arrays.asList(walletFile, password),
                chain3jService,
                NewAccountIdentifier.class);
    }

    @Override
    public Request<?, BooleanResponse> parityRemoveAddress(String accountId) {
        return new Request<>(
                "parity_RemoveAddress",
                Arrays.asList(accountId),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetAccountMeta(
            String accountId, Map<String, Object> metadata) {
        return new Request<>(
                "parity_setAccountMeta",
                Arrays.asList(accountId, metadata),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetAccountName(
            String address, String name) {
        return new Request<>(
                "parity_setAccountName",
                Arrays.asList(address, name),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetDappAddresses(
            String dAppId, ArrayList<String> availableAccountIds) {
        return new Request<>(
                "parity_setDappAddresses",
                Arrays.asList(dAppId, availableAccountIds),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetDappDefaultAddress(
            String dAppId, String defaultAddress) {
        return new Request<>(
                "parity_setDappDefaultAddress",
                Arrays.asList(dAppId, defaultAddress),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetNewDappsAddresses(
            ArrayList<String> availableAccountIds) {
        return new Request<>(
                "parity_setNewDappsAddresses",
                Arrays.asList(availableAccountIds),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> paritySetNewDappsDefaultAddress(String defaultAddress) {
        return new Request<>(
                "parity_setNewDappsDefaultAddress",
                Arrays.asList(defaultAddress),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> parityTestPassword(String accountId, String password) {
        return new Request<>(
                "parity_testPassword",
                Arrays.asList(accountId, password),
                chain3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, PersonalSign> paritySignMessage(
            String accountId, String password, String hexMessage) {
        return new Request<>(
                "parity_signMessage",
                Arrays.asList(accountId,password,hexMessage),
                chain3jService,
                PersonalSign.class);
    }
    
    // TRACE API
    
    @Override
    public Request<?, ParityFullTraceResponse> traceCall(
            Transaction transaction, List<String> traces, DefaultBlockParameter blockParameter) {
        return new Request<>(
            "trace_call",
            Arrays.asList(transaction, traces, blockParameter),
            chain3jService,
            ParityFullTraceResponse.class);
    }
    
    @Override
    public Request<?, ParityFullTraceResponse> traceRawTransaction(
            String data, List<String> traceTypes) {
        return new Request<>(
            "trace_rawTransaction",
            Arrays.asList(data, traceTypes),
            chain3jService,
            ParityFullTraceResponse.class);
    }
    
    @Override
    public Request<?, ParityFullTraceResponse> traceReplayTransaction(
            String hash, List<String> traceTypes) {
        return new Request<>(
            "trace_replayTransaction",
            Arrays.asList(hash, traceTypes),
            chain3jService,
            ParityFullTraceResponse.class);
    }
    
    @Override
    public Request<?, ParityTracesResponse> traceBlock(DefaultBlockParameter blockParameter) {
        return new Request<>(
            "trace_block",
            Arrays.asList(blockParameter),
            chain3jService,
            ParityTracesResponse.class);
    }
    
    @Override
    public Request<?, ParityTracesResponse> traceFilter(TraceFilter traceFilter) {
        return new Request<>(
            "trace_filter",
            Arrays.asList(traceFilter),
            chain3jService,
            ParityTracesResponse.class);
    }
    
    @Override
    public Request<?, ParityTraceGet> traceGet(String hash, List<BigInteger> indices) {
        List<String> encodedIndices = indices.stream()
                .map(Numeric::encodeQuantity)
                .collect(Collectors.toList());
        return new Request<>(
            "trace_get",
            Arrays.asList(hash, encodedIndices),
            chain3jService,
            ParityTraceGet.class);
    }

    @Override
    public Request<?, ParityTracesResponse> traceTransaction(String hash) {
        return new Request<>(
            "trace_transaction",
            Arrays.asList(hash),
            chain3jService,
            ParityTracesResponse.class);
    }
}
