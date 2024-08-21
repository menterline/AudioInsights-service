package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

interface ISpotifyService {

    suspend fun fetchProfile(@RequestHeader("Authorization") bearerToken: String): UserProfile;

    suspend fun fetchTopItems(bearerToken: String, @RequestParam term: String): TopItems

    suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track>;

    suspend fun fetchTopArtists(bearerToken: String, term: String): TopItemsResponse<Artist>;

}