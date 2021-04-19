package command.impl;

import command.CommandHandler;
import dao.impl.aggregator.CommonStatisticAggregator;
import exception.NoProductException;
import exception.NoStoreException;
import exception.ParseCommandException;
import java.util.List;
import model.Store;
import org.bson.Document;
import service.StoreService;
import view.MessageBuilder;

public class ShowProductStatisticCommand implements CommandHandler {

  private final StoreService storeService;
  private final MessageBuilder messageBuilder;

  public ShowProductStatisticCommand(StoreService storeService,
      MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
    this.storeService = storeService;
  }

  @Override
  public void execute(String inputData)
      throws ParseCommandException, NoProductException, NoStoreException {
    List<Document> storeList = storeService.aggregate(new CommonStatisticAggregator());
    storeList.forEach(System.out::println);
  }

}
