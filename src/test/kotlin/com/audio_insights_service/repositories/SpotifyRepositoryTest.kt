package com.audio_insights_service.repositories

import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.mockData.createDummyAudioFeatures
import com.audio_insights_service.repositories.mockData.dummyUserProfile
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.client.MockServerClient
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header.header
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse
import org.mockserver.model.Parameter
import org.springframework.web.reactive.function.client.WebClient
import java.nio.file.Paths

class SpotifyRepositoryTest {

  private lateinit var mockServer: ClientAndServer

  @BeforeEach
  fun startServer() {
    mockServer = ClientAndServer.startClientAndServer(8080) // Start MockServer on port 1080
  }

  @AfterEach
  fun stopServer() {
    mockServer.stop() // Stop MockServer after tests
  }

  @Test
  fun `fetchProfile`() = runBlocking {
    val dummyUserProfile = dummyUserProfile

    val mockProfileJson =
      Paths.get("./")
        .toAbsolutePath()
        .resolve(
          "src/test/kotlin/com/audio_insights_service/repositories/resources/dummyUserProfile.json"
        )
        .toFile()
        .readText()

    MockServerClient("localhost", 8080)
      .`when`(request().withMethod("GET").withPath("/v1/me"))
      .respond(
        HttpResponse.response()
          .withStatusCode(200)
          .withHeader(header("Content-Type", "application/json"))
          .withBody(mockProfileJson)
      )

    val webClient = WebClient.builder().baseUrl("http://localhost:8080").build()
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

    MockServerClient("localhost", 8080)
      .`when`(request().withMethod("GET").withPath("/v1/me/top/tracks"))
      .respond(
        HttpResponse.response()
          .withStatusCode(200)
          .withHeader(header("Content-Type", "application/json"))
          .withBody(mockResponseJson)
      )

    val webClient = WebClient.builder().baseUrl("http://localhost:8080").build()
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

    MockServerClient("localhost", 8080)
      .`when`(request().withMethod("GET").withPath("/v1/me/top/artists"))
      .respond(
        HttpResponse.response()
          .withStatusCode(200)
          .withHeader(header("Content-Type", "application/json"))
          .withBody(mockResponseJson)
      )

    val webClient = WebClient.builder().baseUrl("http://localhost:8080").build()
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

    MockServerClient("localhost", 8080)
      .`when`(
        request()
          .withMethod("GET")
          .withPath("/v1/audio-features")
          .withQueryStringParameters(
            listOf(
              Parameter.param("ids", "13Pjj0X0F6TVP6RaQTRX8rR"),
              Parameter.param("ids", "2dNd2SIsf2G9yZJKUvk8jZ"),
              Parameter.param("ids", "2dNd2SIsf2G9yZJKUvk8jZ"),
            )
          )
      )
      .respond(
        HttpResponse.response()
          .withStatusCode(200)
          .withHeader(header("Content-Type", "application/json"))
          .withBody(mockResponseJson)
      )

    val webClient = WebClient.builder().baseUrl("http://localhost:8080").build()
    val repository = SpotifyRepository(webClient)

    val result =
      repository.fetchTracksAnalysis(
        "Bearer mockToken",
        listOf("13Pjj0X0F6TVP6RaQTRX8rR", "2dNd2SIsf2G9yZJKUvk8jZ", "5nQRjPmBn5foHzXI78qv5K"),
      )
    assertEquals(createDummyAudioFeatures().toString(), result.toString())
  }
}
