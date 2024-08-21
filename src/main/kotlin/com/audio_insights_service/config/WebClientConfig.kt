package com.audio_insights_service.config

import com.audio_insights_service.repositories.ISpotifyRepository
import com.audio_insights_service.repositories.SpotifyRepository
import com.audio_insights_service.services.ISpotifyService
import com.audio_insights_service.services.SpotifyService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun spotifyWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.spotify.com")
            .build()
    }
}

@Configuration
class RepositoryConfig {

    @Bean
    fun spotifyRepository(webClient: WebClient): ISpotifyRepository {
        return SpotifyRepository(webClient)
    }
}

@Configuration
class ServiceConfig {

    @Bean
    fun spotifyService(spotifyRepository: ISpotifyRepository): ISpotifyService {
        return SpotifyService(spotifyRepository)
    }
}