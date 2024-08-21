package com.audio_insights_service.controllers

import com.audio_insights_service.entities.TopItems
import com.audio_insights_service.entities.UserProfile
import com.audio_insights_service.services.ISpotifyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
class SpotifyController(private val spotifyService: ISpotifyService) {

    @GetMapping("/")
    suspend fun fetchProfile(@RequestHeader("Authorization") bearerToken: String): UserProfile  {
        return spotifyService.fetchProfile(bearerToken)
    }

    @GetMapping("/topItems")
    suspend fun fetchTopItems(@RequestHeader("Authorization") bearerToken: String, @RequestParam term: String): TopItems {
        return spotifyService.fetchTopItems(bearerToken, term)
    }
}
