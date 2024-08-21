package com.audio_insights_service.entities

data class TopItems(
    val term: String,
    val artists: List<Artist>,
    val tracks: List<Track>,
    val genres: List<String>
)
