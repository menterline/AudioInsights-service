package com.audio_insights_service.entities

data class TopItemsResponse<T>(
  val items: List<T>,
  val total: Int,
  val limit: Int,
  val offset: Int,
  val href: String,
  val previous: String?,
  val next: String?,
) {
  fun getGenres(artists: List<Artist>): List<String> {
    return artists.flatMap { it.genres.orEmpty() }
  }

  fun convertItems(tracks: List<SpotifyTrack>): TopItemsResponse<Track> {
    return TopItemsResponse(
      items = tracks.map {it.toTrack()},
      total = total,
      limit = limit,
      offset = offset,
      href = href,
      previous = previous,
      next = next
    )
  }
}
