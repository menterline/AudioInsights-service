import com.audio_insights_service.entities.ExternalURL
import com.audio_insights_service.entities.Followers
import com.audio_insights_service.entities.Image

data class Artist(
    val id: String,
    val name: String,
    val href: String,
    val genres: List<String>,
    val popularity: Int,
    val followers: Followers,
    val images: List<Image>,
    val externalUrls: ExternalURL
)