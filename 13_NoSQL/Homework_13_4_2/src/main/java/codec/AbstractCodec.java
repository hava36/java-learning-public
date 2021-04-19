package codec;

import lombok.Getter;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Codec;

@Getter
public abstract class AbstractCodec<V> implements Codec<V> {

  private final BsonDocumentCodec defaultCodec;

  public AbstractCodec() {
    this.defaultCodec = new BsonDocumentCodec();
  }
}
