package Api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ExchangeRateApiResponse(val conversion_rates: Map<String, Double>)

object ExchangeRateApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchExchangeRates(): Map<String, Double> {
        val response: ExchangeRateApiResponse =
            client.get("https://v6.exchangerate-api.com/v6/e9d4a50775b666ce7ea04624/latest/USD")
                .body()
        return response.conversion_rates
    }
    fun close() {
        client.close()
    }
}


