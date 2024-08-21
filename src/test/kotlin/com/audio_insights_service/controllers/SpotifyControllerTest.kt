package com.audio_insights_service.controllers

import com.audio_insights_service.entities.*
import com.audio_insights_service.factories.createDummyTopTracksResponse
import com.audio_insights_service.services.ISpotifyService
import com.github.tomakehurst.wiremock.client.WireMock.*
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.web.reactive.function.client.WebClient
import java.nio.file.Files
import java.nio.file.Paths

class SpotifyControllerTest {

    private val dummyUserProfile = UserProfile(
        country = "US",
        display_name = "John Doe",
        email = "johndoe@example.com",
        explicit_content = ExplicitContent(
            filter_enabled = true,
            filter_locked = false
        ),
        external_urls = ExternalURL(
            spotify = "https://open.spotify.com/user/johndoe"
        ),
        followers = Followers(
            href = "https://api.spotify.com/v1/users/johndoe/followers",
            total = 1500
        ),
        href = "https://api.spotify.com/v1/users/johndoe",
        id = "johndoe",
        images = arrayOf(
            Image(
                url = "https://i.scdn.co/image/abcd1234",
                height = 300,
                width = 300
            )
        ),
        product = "premium",
        type = "user",
        uri = "spotify:user:johndoe"
    )
    @Test
    fun fetchProfile() = runBlocking {
        val service = mockk<ISpotifyService>();
        coEvery {service.fetchProfile("Bearer mockToken")} returns dummyUserProfile
        val controller = SpotifyController(service);
        assertEquals(dummyUserProfile, controller.fetchProfile("Bearer mockToken"))
    }

    @Test
    fun fetchTopItems() = runBlocking {
        val mockTopItemsResponse = TopItems("short_term", listOf(), listOf(), listOf())
        val service = mockk<ISpotifyService>();
        coEvery {service.fetchTopItems("Bearer mockToken", "short_term")} returns mockTopItemsResponse
        val controller = SpotifyController(service);
        assertEquals(mockTopItemsResponse, controller.fetchTopItems("Bearer mockToken", "short_term"))
    }


}