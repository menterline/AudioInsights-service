package com.audio_insights_service.services

import com.audio_insights_service.entities.*

interface ISpotifyService {

    suspend fun fetchProfile( bearerToken: String): UserProfile

    suspend fun fetchTopItems(bearerToken: String, term: String): TopItems

    suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track>

    suspend fun fetchTopArtists(bearerToken: String, term: String): TopItemsResponse<Artist>

    suspend fun fetchTrackAnalysis(bearerToken: String, trackIds: List<String>) : List<TrackAnalysisNode>
}