package com.audio_insights_service.entities

data class ExplicitContent (
    val filter_enabled: Boolean,
    val filter_locked: Boolean
)
data class ExternalURL (
    val spotify: String
)
data class Followers(
    val href: String?,
    val total: Int
)
data class Image(
    val url: String,
    val height: Int?,
    val width: Int?
)