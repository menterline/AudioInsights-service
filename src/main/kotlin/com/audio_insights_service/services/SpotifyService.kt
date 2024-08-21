package com.audio_insights_service.services

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopArtistResponse
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.repositories.ISpotifyRepository
import com.audio_insights_service.repositories.SpotifyRepository
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

class SpotifyService(private val spotifyRepository: ISpotifyRepository): ISpotifyService {

    override suspend fun fetchProfile(bearerToken: String): UserProfile {
        return createTestUserProfile()
    }

    override suspend fun fetchTopItems(bearerToken: String, term: String): TopItems {
        return TopItems(term = term, artists = listOf(), tracks = listOf(), genres = listOf())
    }

    override suspend fun fetchTopTracks(bearerToken: String, term: String): TopItemsResponse<Track> {
        return createDummyTopTracksResponse();
    }

    override suspend fun fetchTopArtists(bearerToken: String, term: String): TopItemsResponse<Artist> {
        return createDummyTopArtistResponse()
    }

}