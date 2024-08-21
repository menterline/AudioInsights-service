package com.audio_insights_service.repositories

import com.audio_insights_service.controllers.SpotifyController
import com.audio_insights_service.entities.*
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.web.reactive.function.client.WebClient
import java.nio.file.Paths

@WireMockTest(httpPort = 8080)
class SpotifyRepositoryTest {
    private val wireMockServerUrl = "http://localhost:8080"
/*
@WireMockTest(httpPort = 8080)

    private val wireMockServerUrl = "http://localhost:8080"

    //Generate mock http server with WireMock and use real webclient
    @Test
    fun fetchProfile() = runBlocking {
        val dummyUserProfile = UserProfile(
            country = "US",
            display_name = "John Doe",
            email = "johndoe@example.com",
            explicit_content = ExplicitContent(
                filter_enabled = true,
                filter_locked = false
            ),
            external_urls = ExternalURL(
                spotify = "https://open.spotify.com/user/johndoe"
            ),
            followers = Followers(
                href = "https://api.spotify.com/v1/users/johndoe/followers",
                total = 1500
            ),
            href = "https://api.spotify.com/v1/users/johndoe",
            id = "johndoe",
            images = arrayOf(
                Image(
                    url = "https://i.scdn.co/image/abcd1234",
                    height = 300,
                    width = 300
                )
            ),
            product = "premium",
            type = "user",
            uri = "spotify:user:johndoe"
        )


        val mockProfileJson = Paths
            .get("./")
            .toAbsolutePath()
            .resolve("src/test/kotlin/com/audio_insights_service/controllers/resources/dummyUserProfile.json")
            .toFile()
            .readText()


        stubFor(
            get("/v1/me").willReturn(okJson(mockProfileJson)))

        val webClient = WebClient.builder()
            .baseUrl(wireMockServerUrl)
            .build()
        // Instantiate the controller with the mocked WebClient
        val controller = SpotifyController(webClient)

        // Call the fetchProfile method
        val result = controller.fetchProfile("Bearer mockToken")

        // Assert that the returned UserProfile matches the mock
        assertEquals(dummyUserProfile.toString(), result.toString())
    }
*/
    @Test
    fun `fetchProfile - need to implement`() = runBlocking {
        assertEquals(1,1)
    }
    @Test
    fun `fetchTopTracks - need to implement`() = runBlocking {
        assertEquals(1, 1)
    }

    @Test
    fun `fetchTopArtists - need to implement`() {
        assertEquals(1,1)
    }
}