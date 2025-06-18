package io.gihub.bsautner.client

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
        val response: HttpResponse = client.request("http://0.0.0.0:8080/test") {
            method = HttpMethod.Get
        }
        return response.bodyAsText()
    }
}