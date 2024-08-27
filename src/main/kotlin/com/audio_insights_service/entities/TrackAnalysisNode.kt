package com.audio_insights_service.entities

data class TrackAnalysisNode (
    val value: Double,
    val key: AudioFeatureKeys,
    val description: String,
)

enum class AudioFeatureKeys {
    DANCEABILITY, ENERGY, LOUDNESS, SPEECHINESS, INSTRUMENTALNESS, LIVENESS
}

val DANCEBILITY_DESCRIPTION = "Describes how suitable a track is for dancing.  Values range for 0 being not danceable at all to 100 being most danceable"
val ENERGY_DESCRIPTION ="Describes a perceptual measure of intensity and activity.  For example, death metal has high energy, while a Bach prelude scores low on the scale."
val LOUDNESS_DESCRIPTION = "Average loudness of the track in dB."
val SPEECHINESS_DESCRIPTION = "Confidence in the track have spoken word.  In an exclusive spoken word track (like a podcast), the value is closer to 1, while going down to zero is an instrumental track."
val INSTRUMENTALNESS_DESCRIPTION = "Confidence value between 0 and 100 that the track is fully instrumental.  Anything above 50 is meant to represent an instrumental track, but confidence is higher as it approaches 100."
val LIVENESS_DESCRIPTION = "Confidence that track is live - higher liveness means a high likelihood that this is a live track."