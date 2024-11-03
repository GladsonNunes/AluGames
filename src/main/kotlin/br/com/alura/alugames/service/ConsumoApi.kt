package br.com.alura.alugames.service

import com.google.gson.Gson
import org.example.br.com.alura.alugames.model.InfoGame
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {
    fun serchGame(id: String): InfoGame? {
        var myInfoGame: InfoGame? = null

        val urlSearch = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(urlSearch))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()

        myInfoGame = gson.fromJson(json, InfoGame::class.java)

    return myInfoGame
   }
}



