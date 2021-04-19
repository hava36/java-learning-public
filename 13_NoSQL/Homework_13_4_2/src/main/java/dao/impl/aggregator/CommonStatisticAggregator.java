package dao.impl.aggregator;

import static utils.MongoUtils.DB_NAME;
import static utils.MongoUtils.STORE_COLLECTION_NAME;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import utils.MongoUtils;

public class CommonStatisticAggregator implements Aggregator<Document> {


  @Override
  public List<Document> aggregate() {
    return MongoUtils.getMongoClient().getDatabase(DB_NAME).getCollection(STORE_COLLECTION_NAME)
        .aggregate(
            Arrays.asList(
                Aggregates.lookup("inventory", "_id", "storeId", "inventory"),
                Aggregates.lookup("product", "inventory.productId", "_id", "product"),
                Aggregates.unwind("$product"),
                Aggregates.group("$title",
                    Accumulators.max("maxPrice", "$product.price"),
                    Accumulators.min("minPrice", "$product.price"),
                    Accumulators.avg("avgPrice", "$product.price"),
                    Accumulators.sum("productCount", 1),
                    Accumulators.sum("priceLess100count", new Document("$cond", Arrays
                        .asList(new Document("$gte", Arrays.asList("$product.price", 100L)), 0L,
                            1L))))
            ))
        .into(new ArrayList<>());
  }
}
