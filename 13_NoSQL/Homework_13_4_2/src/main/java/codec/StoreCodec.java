package codec;

import model.Store;
import org.bson.BsonDocument;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class StoreCodec extends AbstractCodec<Store> {

  @Override
  public Store decode(BsonReader bsonReader, DecoderContext decoderContext) {
    BsonDocument document = getDefaultCodec().decode(bsonReader, decoderContext);
    return new Store(document.get("_id").asString().getValue(),
        document.get("title").asString().getValue());
  }

  @Override
  public void encode(BsonWriter bsonWriter, Store store, EncoderContext encoderContext) {
    bsonWriter.writeStartDocument();
    bsonWriter.writeName("_id");
    bsonWriter.writeString(store.getId());
    bsonWriter.writeName("title");
    bsonWriter.writeString(store.getTitle());
    bsonWriter.writeEndDocument();
  }

  @Override
  public Class<Store> getEncoderClass() {
    return Store.class;
  }

}
