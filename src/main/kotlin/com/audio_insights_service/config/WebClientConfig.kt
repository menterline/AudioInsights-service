package com.audio_insights_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun spotifyWebClient(): WebClient {
        return WebClient.builder().baseUrl("https://api.spotify.com")
            .build()
    }
}