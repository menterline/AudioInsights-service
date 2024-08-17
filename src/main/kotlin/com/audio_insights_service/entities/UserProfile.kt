package com.audio_insights_service.entities

data class UserProfile (
    val country: String,
    val display_name: String,
    val email: String,
    val explicit_content: ExplicitContent,
    val external_urls: ExternalURL,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: Array<Image>,
    val product: String,
    val type: String,
    val uri: String,
)

