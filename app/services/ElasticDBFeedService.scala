package services

import authentication.ElasticSearchClient
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.common.xcontent.XContentType

object ElasticDBFeedService extends App {

  val client = ElasticSearchClient.client
  val jsonString =
    """{
      |"foo":"bar"
    }""".stripMargin
  val indexRequest = new IndexRequest("twitter", "tweets")
    .source(jsonString, XContentType.JSON)

  val indexResponse = client.index(indexRequest, RequestOptions.DEFAULT)

  println(">>>>>id: "+ indexResponse.getId)

  client.close()

}
