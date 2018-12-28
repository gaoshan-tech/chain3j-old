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

public class ScsBlockList extends Response<ScsBlockList.BlockList> {
    @Override
    @JsonDeserialize(using = ScsBlockList.ResponseDeserialiser.class)
    public void setResult(BlockList result) {
        super.setResult(result);
    }

    public BlockList getBlock() {
        return getResult();
    }

    public static class BlockList {
        private String startBlk;
        private String endBlk;
        private String microchainAddress;
        private List<McBlock> blockList;

        public BlockList(String startBlk, String endBlk, String microchainAddress, List<McBlock> blockList) {
            this.startBlk = startBlk;
            this.endBlk = endBlk;
            this.microchainAddress = microchainAddress;
            this.blockList = blockList;
        }

        public BigInteger getStartBlk() {
            return Numeric.decodeQuantity(startBlk);
        }

        public void setStartBlk(String startBlk) {
            this.startBlk = startBlk;
        }

        public BigInteger getEndBlk() {
            return Numeric.decodeQuantity(endBlk);
        }

        public void setEndBlk(String endBlk) {
            this.endBlk = endBlk;
        }

        public String getMicrochainAddress() {
            return microchainAddress;
        }

        public void setMicrochainAddress(String microchainAddress) {
            this.microchainAddress = microchainAddress;
        }

        public List<McBlock> getBlockList() {
            return blockList;
        }

        public void setBlockList(List<McBlock> blockList) {
            this.blockList = blockList;
        }
    }

    public static class ResponseDeserialiser extends JsonDeserializer<BlockList> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public BlockList deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(jsonParser, BlockList.class);
            } else {
                return null;  // null is wrapped by Optional in above getter
            }
        }
    }
}