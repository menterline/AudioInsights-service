package com.audio_insights_service.repositories

import com.audio_insights_service.entities.*
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

interface ISpotifyRepository {

    suspend fun fetchProfile(bearerToken: String): UserProfile;

    suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track> ;

    suspend fun fetchTopArtists(bearerToken: String, term: String): TopItemsResponse<Artist>;

}