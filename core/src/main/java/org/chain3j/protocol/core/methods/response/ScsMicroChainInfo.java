package org.chain3j.protocol.core.methods.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.chain3j.protocol.ObjectMapperFactory;
import org.chain3j.protocol.core.Response;
import org.chain3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class ScsMicroChainInfo extends Response<ScsMicroChainInfo.MicroChainInfo> {
    @Override
    @JsonDeserialize(using = ScsMicroChainInfo.ResponseDeserialiser.class)
    public void setResult(MicroChainInfo result) {
        super.setResult(result);
    }

    public MicroChainInfo getMicroChainInfo() {
        return getResult();
    }


    public static class MicroChainInfo {
        private String balance;
        private String blockReward;
        private String bondLimit;
        private String owner;
        private String txReward;
        private String viaReward;
        private List<String> scsList;

        public MicroChainInfo(String balance, String blockReward, String bondLimit, String owner, String txReward, String viaReward, List<String> scsList) {
            this.balance = balance;
            this.blockReward = blockReward;
            this.bondLimit = bondLimit;
            this.owner = owner;
            this.txReward = txReward;
            this.viaReward = viaReward;
            this.scsList = scsList;
        }

        public BigInteger getBalance() {
            return Numeric.decodeQuantity(balance);
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public BigInteger getBlockReward() {
            return Numeric.decodeQuantity(blockReward);
        }

        public void setBlockReward(String blockReward) {
            this.blockReward = blockReward;
        }

        public BigInteger getBondLimit() {
            return Numeric.decodeQuantity(bondLimit);
        }

        public void setBondLimit(String bondLimit) {
            this.bondLimit = bondLimit;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public BigInteger getTxReward() {
            return Numeric.decodeQuantity(txReward);
        }

        public void setTxReward(String txReward) {
            this.txReward = txReward;
        }

        public BigInteger getViaReward() {
            return Numeric.decodeQuantity(viaReward);
        }

        public void setViaReward(String viaReward) {
            this.viaReward = viaReward;
        }

        public List<String> getScsList() {
            return scsList;
        }

        public void setScsList(List<String> scsList) {
            this.scsList = scsList;
        }
    }

    public static class ResponseDeserialiser extends JsonDeserializer<MicroChainInfo> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public MicroChainInfo deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(jsonParser, MicroChainInfo.class);
            } else {
                return null;  // null is wrapped by Optional in above getter
            }
        }

    }
}
