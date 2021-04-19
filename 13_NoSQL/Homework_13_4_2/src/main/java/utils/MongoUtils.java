package utils;

import codec.StoreCodec;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Inventory;
import model.Product;
import model.Store;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoUtils {

  public static final String STORE_COLLECTION_NAME = "store";
  public static final String INVENTORY_COLLECTION_NAME = "inventory";
  public static final String DB_NAME = "online-store";

  private static final MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
  private static MongoDatabase database;
  private static final MongoCollection<Store> storeCollection = MongoUtils.getDatabase()
      .getCollection(STORE_COLLECTION_NAME, Store.class);

  private static final MongoCollection<Inventory> inventoryCollection = MongoUtils.getDatabase()
      .getCollection(INVENTORY_COLLECTION_NAME, Inventory.class);

  public static MongoDatabase getDatabase() {
    if (database == null) {
      synchronized (MongoUtils.class) {
        database = mongoClient.getDatabase(DB_NAME)
            .withCodecRegistry(
                CodecRegistries.fromRegistries(
                    CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().register(Product.class, Inventory.class,
                            Document.class, BsonDocument.class,
                            BasicDBObject.class)
                            .build(),
                        new Jsr310CodecProvider(),
                        new ValueCodecProvider()),
                    CodecRegistries.fromCodecs(new StoreCodec())));
      }
    }
    return database;
  }

  public static MongoClient getMongoClient() {
    return mongoClient;
  }

  public static MongoCollection<Store> getStoreCollection() {
    return storeCollection;
  }

  public static MongoCollection<Inventory> getInventoryCollection() {
    return inventoryCollection;
  }

}
