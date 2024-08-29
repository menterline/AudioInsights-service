/*
package com.audio_insights_service.repositories

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.mockData.createDummyAudioFeatures
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockserver.client.MockServerClient
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest.request
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.web.reactive.function.client.WebClient
import java.nio.file.Paths

class SpotifyRepositoryTest {

  lateinit var mockServer: ClientAndServer;
@BeforeTestClass
fun startServer() {
  mockServer = ClientAndServer.startClientAndServer(8080)
}


  @Test
  fun `fetchProfile`() = runBlocking {
    val dummyUserProfile =
      UserProfile(
        country = "US",
        display_name = "John Doe",
        email = "johndoe@example.com",
        explicit_content = ExplicitContent(filter_enabled = true, filter_locked = false),
        external_urls = ExternalURL(spotify = "https://open.spotify.com/user/johndoe"),
        followers =
          Followers(href = "https://api.spotify.com/v1/users/johndoe/followers", total = 1500),
        href = "https://api.spotify.com/v1/users/johndoe",
        id = "johndoe",
        images =
          arrayOf(Image(url = "https://i.scdn.co/image/abcd1234", height = 300, width = 300)),
        product = "premium",
        type = "user",
        uri = "spotify:user:johndoe",
      )

    val mockProfileJson =
      Paths.get("./")
        .toAbsolutePath()
        .resolve(
          "src/test/kotlin/com/audio_insights_service/repositories/resources/dummyUserProfile.json"
        )
        .toFile()
        .readText()

    val mockClient = MockServerClient("127.0.0.1", 8080).when(request().withMethod("GET").withPath("/v1/me"))
    stubFor(get("/v1/me").willReturn(okJson(mockProfileJson)))

    val webClient = WebClient.builder().baseUrl(wireMockServerUrl).build()
    val repository = SpotifyRepository(webClient)

    val result = repository.fetchProfile("Bearer mockToken")

    assertEquals(dummyUserProfile.toString(), result.toString())
  }

  @Test
  fun `fetchTopTracks - need to implement`() = runBlocking {
    val mockResponseJson =
      Paths.get("./")
        .toAbsolutePath()
        .resolve(
          "src/test/kotlin/com/audio_insights_service/repositories/resources/dummyTopTracksResponse.json"
        )
        .toFile()
        .readText()

    stubFor(get("/v1/me/top/tracks").willReturn(okJson(mockResponseJson)))

    val webClient = WebClient.builder().baseUrl(wireMockServerUrl).build()
    val repository = SpotifyRepository(webClient)

    val result = repository.fetchTopTracks("Bearer mockToken", "short_term")

    assertEquals(createDummyTopTracksResponse().toString(), result.toString())
  }

  @Test
  fun `fetchTopArtists`() = runBlocking {
    val mockResponseJson =
      Paths.get("./")
        .toAbsolutePath()
        .resolve(
          "src/test/kotlin/com/audio_insights_service/repositories/resources/dummyTopArtistsResponse.json"
        )
        .toFile()
        .readText()

    stubFor(get("/v1/me/top/artists").willReturn(okJson(mockResponseJson)))

    val webClient = WebClient.builder().baseUrl(wireMockServerUrl).build()
    val repository = SpotifyRepository(webClient)

    val result = repository.fetchTopArtists("Bearer mockToken", "short_term")

    assertEquals(createDummyTopArtistResponse().toString(), result.toString())
  }

  @Test
  fun `fetchTracksAnalysis`() = runBlocking {
    val mockResponseJson =
      Paths.get("./")
        .toAbsolutePath()
        .resolve(
          "src/test/kotlin/com/audio_insights_service/repositories/resources/dummyAudioFeaturesResponse.json"
        )
        .toFile()
        .readText()

    stubFor(get("/v1/audio-features?ids=13Pjj0X0F6TVP6RaQTRX8rR&ids=2dNd2SIsf2G9yZJKUvk8jZ&ids=5nQRjPmBn5foHzXI78qv5K").willReturn(okJson(mockResponseJson)))

    val webClient = WebClient.builder().baseUrl(wireMockServerUrl).build()
    val repository = SpotifyRepository(webClient)

    val result= repository.fetchTracksAnalysis("Bearer mockToken", listOf("13Pjj0X0F6TVP6RaQTRX8rR", "2dNd2SIsf2G9yZJKUvk8jZ", "5nQRjPmBn5foHzXI78qv5K"))
    assertEquals(createDummyAudioFeatures().toString(), result.toString())
  }
}
*/
