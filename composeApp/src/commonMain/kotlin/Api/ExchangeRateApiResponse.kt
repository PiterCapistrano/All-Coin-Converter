package Api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateApiResponse(
    val conversion_rates: Map<String, Double>,
    val base: String,
    val date: String
    )
@Serializable
object ExchangeRateApiClient {

    suspend fun fetchExchangeRates(): Map<String, Double> {
        val client = HttpClient{
            install(ContentNegotiation){
                json()
            }
            install(HttpCache)
        }
        val response: ExchangeRateApiResponse =
            client.get("https://v6.exchangerate-api.com/v6/e9d4a50775b666ce7ea04624/latest/USD")
                .body()
        return response.conversion_rates
    }
}


