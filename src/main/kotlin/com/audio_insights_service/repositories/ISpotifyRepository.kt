package com.audio_insights_service.repositories

import com.audio_insights_service.entities.*

interface ISpotifyRepository {

    suspend fun fetchProfile(bearerToken: String): UserProfile;

    suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track> ;

    suspend fun fetchTopArtists(bearerToken: String, term: String): TopItemsResponse<Artist>;

    suspend fun fetchTracksAnalysis(bearerToken: String, trackIds: List<String>) : AudioFeaturesSpotifyResponse
}