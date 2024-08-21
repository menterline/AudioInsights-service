package com.audio_insights_service.repositories

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.nio.file.Paths
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

@WireMockTest(httpPort = 8080)
class SpotifyRepositoryTest {
  private val wireMockServerUrl = "http://localhost:8080"

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

    stubFor(get("/v1/me").willReturn(okJson(mockProfileJson)))

    val webClient = WebClient.builder().baseUrl(wireMockServerUrl).build()
    val repository = SpotifyRepository(webClient)

    val result = repository.fetchProfile("Bearer mockToken")

    assertEquals(dummyUserProfile.toString(), result.toString())
  }

  @Test fun `fetchTopTracks - need to implement`() = runBlocking {
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

    assertEquals(createDummyTopArtistResponse().toString(), result.toString())  }
}
