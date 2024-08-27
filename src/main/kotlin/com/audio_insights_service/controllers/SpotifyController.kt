package com.audio_insights_service.controllers

import com.audio_insights_service.entities.TopItems
import com.audio_insights_service.entities.TrackAnalysisNode
import com.audio_insights_service.entities.UserProfile
import com.audio_insights_service.services.ISpotifyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = ["http://localhost:5173"])
class SpotifyController(private val spotifyService: ISpotifyService) {

    @GetMapping("/")
    suspend fun fetchProfile(@RequestHeader("Authorization") bearerToken: String): UserProfile  {
        return spotifyService.fetchProfile(bearerToken)
    }

    @GetMapping("/topItems")
    suspend fun fetchTopItems(@RequestHeader("Authorization") bearerToken: String, @RequestParam term: String): TopItems {
        return spotifyService.fetchTopItems(bearerToken, term)
    }

    @GetMapping("/tracksAnalysis")
    suspend fun fetchTracksAnalysis(@RequestHeader("Authorization") bearerToken: String, @RequestParam ids: List<String>): List<TrackAnalysisNode> {
        return spotifyService.fetchTrackAnalysis(bearerToken, ids)
    }


}
