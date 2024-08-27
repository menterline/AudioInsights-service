package com.audio_insights_service.repositories.mockData

import com.audio_insights_service.entities.AudioFeatures
import com.audio_insights_service.entities.AudioFeaturesSpotifyResponse

fun createDummyAudioFeatures(): AudioFeaturesSpotifyResponse {
    return AudioFeaturesSpotifyResponse(listOf(
        AudioFeatures(
            danceability = 0.712,
            energy = 0.888,
            key = 7,
            loudness = -4.996,
            mode = 1,
            speechiness = 0.0621,
            acousticness = 0.61,
            instrumentalness = 0.000614,
            liveness = 0.116,
            valence = 0.51,
            tempo = 100.0,
            type = "audio_features",
            id = "3Pjj0X0F6TVP6RaQTRX8rR",
            uri = "spotify:track:3Pjj0X0F6TVP6RaQTRX8rR",
            trackHref = "https://api.spotify.com/v1/tracks/3Pjj0X0F6TVP6RaQTRX8rR",
            analysisUrl = "https://api.spotify.com/v1/audio-analysis/3Pjj0X0F6TVP6RaQTRX8rR",
            durationMs = 216213,
            timeSignature = 4
        ),
        AudioFeatures(
            danceability = 0.504,
            energy = 0.826,
            key = 10,
            loudness = -3.105,
            mode = 1,
            speechiness = 0.0816,
            acousticness = 0.217,
            instrumentalness = 0.000359,
            liveness = 0.0899,
            valence = 0.348,
            tempo = 104.498,
            type = "audio_features",
            id = "2dNd2SIsf2G9yZJKUvk8jZ",
            uri = "spotify:track:2dNd2SIsf2G9yZJKUvk8jZ",
            trackHref = "https://api.spotify.com/v1/tracks/2dNd2SIsf2G9yZJKUvk8jZ",
            analysisUrl = "https://api.spotify.com/v1/audio-analysis/2dNd2SIsf2G9yZJKUvk8jZ",
            durationMs = 254247,
            timeSignature = 4
        ),
        AudioFeatures(
            danceability = 0.527,
            energy = 0.566,
            key = 2,
            loudness = -8.021,
            mode = 1,
            speechiness = 0.284,
            acousticness = 0.00239,
            instrumentalness = 0.0191,
            liveness = 0.0741,
            valence = 0.397,
            tempo = 94.859,
            type = "audio_features",
            id = "5nQRjPmBn5foHzXI78qv5K",
            uri = "spotify:track:5nQRjPmBn5foHzXI78qv5K",
            trackHref = "https://api.spotify.com/v1/tracks/5nQRjPmBn5foHzXI78qv5K",
            analysisUrl = "https://api.spotify.com/v1/audio-analysis/5nQRjPmBn5foHzXI78qv5K",
            durationMs = 253376,
            timeSignature = 4
        )
    ))
}