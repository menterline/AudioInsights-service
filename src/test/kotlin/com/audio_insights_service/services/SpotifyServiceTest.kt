package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.ISpotifyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SpotifyServiceTest {

  @Test fun fetchProfile() = runBlocking {
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
  @Test fun fetchTopItems() = runBlocking {
    val mockTracksResponse = createDummyTopTracksResponse()
    val mockArtistsResponse = createDummyTopArtistResponse()
    val repository = mockk<ISpotifyRepository>()

    coEvery { repository.fetchTopTracks("ABC", "short_term") } returns mockTracksResponse
    coEvery { repository.fetchTopArtists("ABC", "short_term") } returns mockArtistsResponse
    val service = SpotifyService(repository)
    val expected = TopItems(term = "short_term", artists = mockArtistsResponse.items, tracks = mockTracksResponse.items, genres = listOf("pop", "rock"))
    assertEquals(expected, service.fetchTopItems("ABC", "short_term") )
  }

  @Test fun fetchTopTracks() = runBlocking{
    val mockResponse = createDummyTopTracksResponse()
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchTopTracks("ABC", "short_term") } returns mockResponse
    val service = SpotifyService(repository)
    assertEquals(mockResponse, service.fetchTopTracks("ABC", "short_term"))

  }

  @Test fun fetchTopArtists()= runBlocking {
    val mockResponse = createDummyTopArtistResponse()
    val repository = mockk<ISpotifyRepository>()
    coEvery { repository.fetchTopArtists("ABC", "short_term") } returns mockResponse
    val service = SpotifyService(repository)
    assertEquals(mockResponse, service.fetchTopArtists("ABC", "short_term"))}
}
