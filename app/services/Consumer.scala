package services

import java.time.Duration
import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}


object Consumer {

  val server = "localhost:9092"
  val properties = new Properties()
  val topic = "twitter_tweets"
  properties.put("bootstrap.servers", server)

  //create config for consumer
  properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  properties.put("group.id", "twitter_elasticsearch")
  properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") // read data from beginning

  //create consumer
  val kafkaConsumer = new KafkaConsumer[String, String](properties)

  //subscribe to our topic
  kafkaConsumer.subscribe(util.Collections.singletonList(topic))

}
