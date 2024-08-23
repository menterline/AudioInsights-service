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
  // hacky solution so that we can only call this with lists of artists
  fun getGenres(artists: List<Artist>): List<String> {
    return artists.flatMap { it.genres }
  }
}
