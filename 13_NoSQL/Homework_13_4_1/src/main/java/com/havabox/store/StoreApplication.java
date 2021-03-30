package com.havabox.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class StoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(StoreApplication.class, args);
  }

}
