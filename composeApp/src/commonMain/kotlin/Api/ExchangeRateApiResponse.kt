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

suspend fun getExchangeRates(apiKey: String): ExchangeRateApiResponse {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val response: ExchangeRateApiResponse = client.get("https://v6.exchangerate-api.com/v6/e9d4a50775b666ce7ea04624/latest/USD").body()
    client.close()
    return response
}

fun main() = kotlinx.coroutines.runBlocking {
    val apiKey = "e9d4a50775b666ce7ea04624"
    val exchangeRates = getExchangeRates(apiKey)
    println(exchangeRates.conversion_rates)
}