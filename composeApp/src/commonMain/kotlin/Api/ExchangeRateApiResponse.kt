package Api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateApiResponse(val conversion_rates: Map<String, Double>)

suspend fun getExchangeRates(apiKey: String): ExchangeRateApiResponse {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    val response: ExchangeRateApiResponse = client.get("https://v6.exchangerate-api.com/v6/e9d4a50775b666ce7ea04624/latest/USD")
    client.close()
    return response
}