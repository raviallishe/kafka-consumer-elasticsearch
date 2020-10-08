package authentication

import org.apache.http.auth.{AuthScope, UsernamePasswordCredentials}
import org.apache.http.impl.client.BasicCredentialsProvider
import config.ApplicationConfig._
import org.apache.http.HttpHost
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

object ElasticSearchClient {

  val credentialsProvider = new BasicCredentialsProvider()
  //don't do if you run on local elasticsearch(here we're using EL hosted on bonsai.com)
  credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password))

  private val builder = RestClient.builder(new HttpHost(hostName, 443, "https"))
    .setHttpClientConfigCallback(new HttpClientConfigCallback() {
      override def customizeHttpClient(httpClientBuilder: HttpAsyncClientBuilder): HttpAsyncClientBuilder =
        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
    })

  //Java High-Level REST client — It is based on low-level client and exposes API specific methods, taking care of requests marshalling and responses un-marshalling.
  val client = new RestHighLevelClient(builder)
}
