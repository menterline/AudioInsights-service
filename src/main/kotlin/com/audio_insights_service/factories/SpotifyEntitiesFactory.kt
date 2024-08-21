package com.audio_insights_service.factories

import com.audio_insights_service.entities.*

    fun createDummyTopTracksResponse(): TopItemsResponse<Track> {
            val dummyImage = Image(
                url = "https://example.com/image.jpg",
                height = 640,
                width = 640
            )

            val dummyAlbum = Album(
                id = "album_id",
                name = "Dummy Album",
                images = listOf(dummyImage),
                release_date = "2024-01-01",
                total_tracks = 10,
                href = "https://api.spotify.com/v1/albums/album_id",
                uri = "spotify:album:album_id"
            )

            val dummyArtist = Artist(
                id = "artist_id",
                name = "Dummy Artist",
                genres = listOf("pop", "rock"),
                href = "https://api.spotify.com/v1/artists/artist_id",
                images = listOf(dummyImage),
                popularity = 80,
                type = "artist",
                uri = "spotify:artist:artist_id"
            )

            val dummyTrack = Track(
                id = "track_id",
                name = "Dummy Track",
                album = dummyAlbum,
                artists = listOf(dummyArtist),
                duration_ms = 180000,
                explicit = false,
                href = "https://api.spotify.com/v1/tracks/track_id",
                popularity = 90,
                preview_url = "https://p.scdn.co/mp3-preview/track_preview",
                track_number = 1,
                type = "track",
                uri = "spotify:track:track_id"
            )

            return TopItemsResponse(
                items = listOf(dummyTrack),
                total = 1,
                limit = 20,
                offset = 0,
                href = "https://api.spotify.com/v1/me/top/tracks",
                previous = null,
                next = null
            )

    }

fun createDummyTopArtistResponse(): TopItemsResponse<Artist> {
    val dummyImage = Image(
        url = "https://example.com/artist_image.jpg",
        height = 640,
        width = 640
    )

    val dummyArtist = Artist(
        id = "artist_id",
        name = "Dummy Artist",
        genres = listOf("pop", "rock"),
        href = "https://api.spotify.com/v1/artists/artist_id",
        images = listOf(dummyImage),
        popularity = 80,
        type = "artist",
        uri = "spotify:artist:artist_id"
    )

    return TopItemsResponse(
        items = listOf(dummyArtist),
        total = 1,
        limit = 20,
        offset = 0,
        href = "https://api.spotify.com/v1/me/top/artists",
        previous = null,
        next = null
    )
}