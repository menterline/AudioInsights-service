package com.audio_insights_service.controllers

import com.audio_insights_service.entities.*
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody


@RestController
@RequestMapping("/api/profile")
class SpotifyController() {

    @GetMapping("/")
    suspend fun fetchProfile(@RequestHeader("Authorization") bearerToken: String): UserProfile  {
        val client = WebClient.create("https://api.spotify.com")
        val req = client.get()
            .uri("/v1/me").accept(MediaType.APPLICATION_JSON)
            .header("Authorization", bearerToken)
        val result = req
            .retrieve()
            .awaitBody<UserProfile>()
        return result
    }
}
