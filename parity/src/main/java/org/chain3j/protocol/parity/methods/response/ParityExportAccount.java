package org.chain3j.protocol.parity.methods.response;

import org.chain3j.crypto.WalletFile;
import org.chain3j.protocol.core.Response;

/**
 * parity_ExportAccount.
 */
public class ParityExportAccount extends Response<WalletFile> {
    public WalletFile getWallet() {
        return getResult();
    }
}
