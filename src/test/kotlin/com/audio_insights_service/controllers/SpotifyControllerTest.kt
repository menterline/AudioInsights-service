package com.audio_insights_service.controllers

import com.audio_insights_service.entities.*
import com.audio_insights_service.services.ISpotifyService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SpotifyControllerTest {


    @Test
    fun fetchProfile() = runBlocking {
        val service = mockk<ISpotifyService>();
        coEvery {service.fetchProfile("Bearer mockToken")} returns dummyUserProfile
        val controller = SpotifyController(service);
        assertEquals(dummyUserProfile, controller.fetchProfile("Bearer mockToken"))
    }

    @Test
    fun fetchTopItems() = runBlocking {
        val mockTopItemsResponse = TopItems("short_term", listOf(), listOf(), listOf())
        val service = mockk<ISpotifyService>();
        coEvery {service.fetchTopItems("Bearer mockToken", "short_term")} returns mockTopItemsResponse
        val controller = SpotifyController(service);
        assertEquals(mockTopItemsResponse, controller.fetchTopItems("Bearer mockToken", "short_term"))
    }

    @Test
    fun fetchTracksAnalysis() = runBlocking {
        val expectedAvgDanceability = (.712 + .504 + .527) / 3
        val expectedAvgEnergy = (.888 + .826 + .566) / 3
        val expectedAvgLoudness = (-4.996 + -3.105 + -8.021) / 3
        val expectedAvgSpeechiness = (.0621 + .0816 + .284) / 3
        val expectedAvgInstrumentalness = (.000614 + .000359 + .0191) / 3
        val expectedAvgLiveness = (.116 + .0899 + .0741) / 3

        val mockServiceResult = listOf(
            TrackAnalysisNode(expectedAvgDanceability, AudioFeatureKeys.DANCEABILITY, DANCEBILITY_DESCRIPTION),
            TrackAnalysisNode(expectedAvgEnergy, AudioFeatureKeys.ENERGY, ENERGY_DESCRIPTION),
            TrackAnalysisNode(expectedAvgLoudness, AudioFeatureKeys.LOUDNESS, LOUDNESS_DESCRIPTION),
            TrackAnalysisNode(expectedAvgSpeechiness, AudioFeatureKeys.SPEECHINESS, SPEECHINESS_DESCRIPTION),
            TrackAnalysisNode(expectedAvgInstrumentalness, AudioFeatureKeys.INSTRUMENTALNESS, INSTRUMENTALNESS_DESCRIPTION),
            TrackAnalysisNode(expectedAvgLiveness, AudioFeatureKeys.LIVENESS, LIVENESS_DESCRIPTION),
        )

        val service = mockk<ISpotifyService>()
        coEvery { service.fetchTrackAnalysis("Bearer mockToken", listOf("1", "2", "3"))} returns mockServiceResult
        val controller = SpotifyController(service)
        assertEquals(mockServiceResult, controller.fetchTracksAnalysis("Bearer mockToken", listOf("1", "2", "3")))
    }

    private val dummyUserProfile = UserProfile(
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


}