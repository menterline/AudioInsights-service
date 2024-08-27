package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.ISpotifyRepository
import com.audio_insights_service.repositories.mockData.createDummyAudioFeatures
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SpotifyServiceTest {

  @Test
  fun fetchProfile() = runBlocking {
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
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchProfile("ABC") } returns dummyUserProfile
    val service = SpotifyService(repository)
    assertEquals(dummyUserProfile, service.fetchProfile("ABC"))
  }

  @Test
  fun fetchTopItems() = runBlocking {
    val mockTracksResponse = createDummyTopTracksResponse()
    val mockArtistsResponse = createDummyTopArtistResponse()
    val repository = mockk<ISpotifyRepository>()

    coEvery { repository.fetchTopTracks("ABC", "short_term") } returns mockTracksResponse
    coEvery { repository.fetchTopArtists("ABC", "short_term") } returns mockArtistsResponse
    val service = SpotifyService(repository)
    val expected =
      TopItems(
        term = "short_term",
        artists = mockArtistsResponse.items,
        tracks = mockTracksResponse.items,
        genres = listOf("pop", "rock"),
      )
    assertEquals(expected, service.fetchTopItems("ABC", "short_term"))
  }

  @Test
  fun fetchTopTracks() = runBlocking {
    val mockResponse = createDummyTopTracksResponse()
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchTopTracks("ABC", "short_term") } returns mockResponse
    val service = SpotifyService(repository)
    assertEquals(mockResponse, service.fetchTopTracks("ABC", "short_term"))
  }

  @Test
  fun fetchTopArtists() = runBlocking {
    val mockResponse = createDummyTopArtistResponse()
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchTopArtists("ABC", "short_term") } returns mockResponse
    val service = SpotifyService(repository)
    assertEquals(mockResponse, service.fetchTopArtists("ABC", "short_term"))
  }

  @Test
  fun fetchTracksAnalysis() = runBlocking {
    val mockResponse = createDummyAudioFeatures()
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchTracksAnalysis("ABC", listOf("1", "2", "3")) } returns mockResponse
    val service = SpotifyService(repository)
    val expectedAvgDanceability = (.712 + .504 + .527) / 3
    val expectedAvgEnergy = (.888 + .826 + .566) / 3
    val expectedAvgLoudness = (-4.996 + -3.105 + -8.021) / 3
    val expectedAvgSpeechiness = (.0621 + .0816 + .284) / 3
    val expectedAvgInstrumentalness = (.000614 + .000359 + .0191) / 3
    val expectedAvgLiveness = (.116 + .0899 + .0741) / 3

    val expectedNodes = listOf(
      TrackAnalysisNode(expectedAvgDanceability, AudioFeatureKeys.DANCEABILITY, DANCEBILITY_DESCRIPTION),
      TrackAnalysisNode(expectedAvgEnergy, AudioFeatureKeys.ENERGY, ENERGY_DESCRIPTION),
      TrackAnalysisNode(expectedAvgLoudness, AudioFeatureKeys.LOUDNESS, LOUDNESS_DESCRIPTION),
      TrackAnalysisNode(expectedAvgSpeechiness, AudioFeatureKeys.SPEECHINESS, SPEECHINESS_DESCRIPTION),
      TrackAnalysisNode(expectedAvgInstrumentalness, AudioFeatureKeys.INSTRUMENTALNESS, INSTRUMENTALNESS_DESCRIPTION),
      TrackAnalysisNode(expectedAvgLiveness, AudioFeatureKeys.LIVENESS, LIVENESS_DESCRIPTION),
    )
    assertEquals(expectedNodes, service.fetchTrackAnalysis("ABC", listOf("1", "2" , "3")))
  }
}
