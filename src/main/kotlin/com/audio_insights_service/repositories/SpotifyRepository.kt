package com.audio_insights_service.repositories

import com.audio_insights_service.entities.*
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class SpotifyRepository(private val spotifyWebClient: WebClient) : ISpotifyRepository {

  override suspend fun fetchProfile(bearerToken: String): UserProfile {
    val req =
      spotifyWebClient
        .get()
        .uri("/v1/me")
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", bearerToken)
    val result = req.retrieve().awaitBody<UserProfile>()
    return result
  }

  override suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track> {
    val req =
      spotifyWebClient
        .get()
        .uri("/v1/me/top/tracks")
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", bearerToken)
    val result = req.retrieve().awaitBody<TopItemsResponse<Track>>()
    return result
  }

  override suspend fun fetchTopArtists(
    bearerToken: String,
    term: String,
  ): TopItemsResponse<Artist> {
    val req =
      spotifyWebClient
        .get()
        .uri("/v1/me/top/artists")
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", bearerToken)
    val result = req.retrieve().awaitBody<TopItemsResponse<Artist>>()
    return result
  }

  override suspend fun fetchTracksAnalysis(
    bearerToken: String,
    trackIds: List<String>,
  ): AudioFeaturesSpotifyResponse {
    val req =
      spotifyWebClient
        .get()
        .uri("/v1/audio-features") { uriBuilder -> uriBuilder.queryParam("ids", trackIds).build() }
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", bearerToken)
    val result = req.retrieve().awaitBody<AudioFeaturesSpotifyResponse>()
    return result
  }
}
