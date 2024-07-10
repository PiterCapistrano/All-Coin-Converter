package Api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class Server {
    suspend fun getCoins(): Flow<MutableList<Coins>>{
        val client = HttpClient {
            install(ContentNegotiation){
                json()
            }
            install(HttpCache)
        }
        val coins = client
            .get("https://v6.exchangerate-api.com/v6/e9d4a50775b666ce7ea04624/latest/USD")
            .body<MutableList<Coins>>()
        return MutableStateFlow(coins)
    }
}