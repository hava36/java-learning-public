package com.havabox.store.config;

import com.havabox.store.queue.MessagePublisher;
import com.havabox.store.queue.MessagePublisherImpl;
import com.havabox.store.queue.MessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }

  @Bean
  public RedisMessageListenerContainer redisContainer() {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory());
    container.addMessageListener(messageListener(), topic());
    return container;
  }

  @Bean
  public MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new MessageSubscriber());
  }

  @Bean
  public MessagePublisher redisPublisher() {
    return new MessagePublisherImpl(redisTemplate(), topic());
  }

  @Bean
  public ChannelTopic topic() {
    return new ChannelTopic("pubsub:queue");
  }

}
