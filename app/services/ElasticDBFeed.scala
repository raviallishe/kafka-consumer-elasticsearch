package services

import java.time.Duration

import authentication.ElasticSearchClient
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.common.xcontent.XContentType
import services.Consumer.kafkaConsumer
import scala.collection.JavaConverters._


object ElasticDBFeed extends App {

  val client = ElasticSearchClient.client
  val jsonString =
    """{
      |"foo":"bar"
    }""".stripMargin


  //poll for data
  while (true) {
    val records = kafkaConsumer.poll(Duration.ofMillis(100))
    for (record <- records.asScala) {
      val indexRequest = new IndexRequest("twitter", "tweets")
        .source(record.value(), XContentType.JSON)

      val indexResponse = client.index(indexRequest, RequestOptions.DEFAULT)
      Thread.sleep(2000)
      println(">>>>>id: "+ indexResponse.getId)
    }
  }


  client.close()

}
