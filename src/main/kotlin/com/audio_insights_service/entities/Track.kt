package com.audio_insights_service.entities

data class Track(
    val id: String,
    val name: String,
    val album: Album,
    val artists: List<Artist>,
    val href: String,
    val popularity: Int,
    val previewUrl: String?,
    val type: String,
    val spotifyUrl: String?
)
