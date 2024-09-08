package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import com.audio_insights_service.repositories.ISpotifyRepository
import org.springframework.stereotype.Service

@Service
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
    return spotifyRepository.fetchTopTracks(bearerToken, term);
  }

  override suspend fun fetchTopArtists(
    bearerToken: String,
    term: String,
  ): TopItemsResponse<Artist> {
    return spotifyRepository.fetchTopArtists(bearerToken, term);
  }

  override suspend fun fetchTrackAnalysis(bearerToken: String, trackIds: List<String>): List<TrackAnalysisNode> {
    val audioFeatures = spotifyRepository.fetchTracksAnalysis(bearerToken, trackIds);
    val avgDanceability = audioFeatures.audio_features.map { it -> it.danceability }.average()
    val avgEnergy = audioFeatures.audio_features.map { it -> it.energy }.average()
    val avgLoudness = audioFeatures.audio_features.map { it -> it.loudness }.average()
    val avgSpeechiness = audioFeatures.audio_features.map { it -> it.speechiness }.average()
    val avgInstrumentalness = audioFeatures.audio_features.map { it -> it.instrumentalness }.average()
    val avgLiveness = audioFeatures.audio_features.map { it -> it.liveness }.average()
    return listOf(
      TrackAnalysisNode(avgDanceability * 100, AudioFeatureKeys.DANCEABILITY, DANCEBILITY_DESCRIPTION),
      TrackAnalysisNode(avgEnergy * 100, AudioFeatureKeys.ENERGY, ENERGY_DESCRIPTION),
      TrackAnalysisNode(avgLoudness, AudioFeatureKeys.LOUDNESS, LOUDNESS_DESCRIPTION),
      TrackAnalysisNode(avgSpeechiness * 100, AudioFeatureKeys.SPEECHINESS, SPEECHINESS_DESCRIPTION),
      TrackAnalysisNode(avgInstrumentalness * 100, AudioFeatureKeys.INSTRUMENTALNESS, INSTRUMENTALNESS_DESCRIPTION),
      TrackAnalysisNode(avgLiveness * 100, AudioFeatureKeys.LIVENESS, LIVENESS_DESCRIPTION),
    )
  }
}
