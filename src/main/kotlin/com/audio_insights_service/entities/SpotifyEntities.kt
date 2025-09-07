package com.audio_insights_service.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

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


data class Artist(
    val id: String,
    val name: String,
    val genres: List<String>?,
    val href: String,
    val images: List<Image>?,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class SpotifyTrack(
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
    val uri: String,
    @JsonProperty("external_urls")
    val externalUrls: LinkedHashMap<String, String>
) {
    fun toTrack(): Track {
        return Track(
            id = id,
            name = name,
            album = album,
            artists = artists,
            href = href,
            popularity = popularity,
            previewUrl = preview_url,
            type = type,
            spotifyUrl = externalUrls.get("spotify")
        )
    }
}

data class Album(
    val id: String,
    val name: String,
    val images: List<Image>,
    val release_date: String,
    val total_tracks: Int,
    val href: String,
    val uri: String
)
