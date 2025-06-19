package io.github.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

interface KrillClient {
    suspend fun testGet() : String
}

class DefaultKrillClient(private val client: HttpClient

) : KrillClient  {


    override suspend fun testGet() :String {
        val response: HttpResponse = client.request("http://pi-krill.local:8080/test") {
            method = HttpMethod.Get
        }
        return response.bodyAsText()
    }
}