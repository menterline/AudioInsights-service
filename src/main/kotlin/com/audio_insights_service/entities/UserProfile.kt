package com.audio_insights_service.entities

data class UserProfile (
    val country: String,
    val display_name: String,
    val email: String,
    val explicit_content: ExplicitContent,
    val external_urls: ExternalURL,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: Array<Image>,
    val product: String,
    val type: String,
    val uri: String,
)

fun createTestUserProfile(): UserProfile {
    return UserProfile(
        country = "US",
        display_name = "John Doe",
        email = "johndoe@example.com",
        explicit_content = ExplicitContent(
            filter_enabled = true,
            filter_locked = false
        ),
        external_urls = ExternalURL(
            spotify = "https://open.spotify.com/user/johndoe"
        ),
        followers = Followers(
            href = "https://api.spotify.com/v1/users/johndoe/followers",
            total = 1500
        ),
        href = "https://api.spotify.com/v1/users/johndoe",
        id = "johndoe",
        images = arrayOf(
            Image(
                url = "https://i.scdn.co/image/abcd1234",
                height = 300,
                width = 300
            )
        ),
        product = "premium",
        type = "user",
        uri = "spotify:user:johndoe"
    )
}

