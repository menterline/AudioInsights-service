package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.ISpotifyRepository

class SpotifyService(private val spotifyRepository: ISpotifyRepository) : ISpotifyService {

  override suspend fun fetchProfile(bearerToken: String): UserProfile {
    return spotifyRepository.fetchProfile(bearerToken)
  }

  /*
  combine results from topTracksAndTopArtists with just the important stuff
   */
  override suspend fun fetchTopItems(bearerToken: String, term: String): TopItems {
    val tracks = fetchTopTracks(bearerToken, term)
    val artists = fetchTopArtists(bearerToken, term)

    return TopItems(
      term = term,
      artists = artists.items,
      tracks = tracks.items,
      genres = artists.getGenres(artists = artists.items),
    )
  }

  override suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track> {
    return createDummyTopTracksResponse()
  }

  override suspend fun fetchTopArtists(
    bearerToken: String,
    term: String,
  ): TopItemsResponse<Artist> {
    return createDummyTopArtistResponse()
  }
}
