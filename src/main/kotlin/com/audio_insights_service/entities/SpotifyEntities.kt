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

data class TopItemsResponse<T>(
    val items: List<T>,
    val total: Int,
    val limit: Int,
    val offset: Int,
    val href: String,
    val previous: String?,
    val next: String?
)

data class Artist(
    val id: String,
    val name: String,
    val genres: List<String>,
    val href: String,
    val images: List<Image>,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class Track(
    val id: String,
    val name: String,
    val album: Album,
    val artists: List<Artist>,
    val duration_ms: Int,
    val explicit: Boolean,
    val href: String,
    val popularity: Int,
    val preview_url: String?,
    val track_number: Int,
    val type: String,
    val uri: String
)

data class Album(
    val id: String,
    val name: String,
    val images: List<Image>,
    val release_date: String,
    val total_tracks: Int,
    val href: String,
    val uri: String
)
