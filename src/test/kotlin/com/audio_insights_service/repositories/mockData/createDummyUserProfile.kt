package com.audio_insights_service.repositories.mockData

import com.audio_insights_service.entities.*

val dummyUserProfile =
    UserProfile(
        country = "US",
        display_name = "John Doe",
        email = "johndoe@example.com",
        explicit_content = ExplicitContent(filter_enabled = true, filter_locked = false),
        external_urls = ExternalURL(spotify = "https://open.spotify.com/user/johndoe"),
        followers =
        Followers(href = "https://api.spotify.com/v1/users/johndoe/followers", total = 1500),
        href = "https://api.spotify.com/v1/users/johndoe",
        id = "johndoe",
        images =
        arrayOf(Image(url = "https://i.scdn.co/image/abcd1234", height = 300, width = 300)),
        product = "premium",
        type = "user",
        uri = "spotify:user:johndoe",
    )
