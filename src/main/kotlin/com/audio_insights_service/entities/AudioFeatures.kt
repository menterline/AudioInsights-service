package com.audio_insights_service.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class AudioFeatures(
    val danceability: Double,
    val energy: Double,
    val key: Int,
    val loudness: Double,
    val mode: Int,
    val speechiness: Double,
    val acousticness: Double,
    val instrumentalness: Double,
    val liveness: Double,
    val valence: Double,
    val tempo: Double,
    val type: String,
    val id: String,
    val uri: String,
    @JsonProperty("track_href") val trackHref: String,
    @JsonProperty("analysis_url") val analysisUrl: String,
    @JsonProperty("duration_ms") val durationMs: Int,
    @JsonProperty("time_signature") val timeSignature: Int
)

data class AudioFeaturesSpotifyResponse(
    val audio_features: List<AudioFeatures>
)