package com.jabillo.csc422midterm.data.quotes.repository

import com.jabillo.csc422midterm.data.quotes.model.Quote
import com.jabillo.csc422midterm.data.quotes.model.QuoteResponse
import com.jabillo.csc422midterm.network.HttpRoutes
import com.jabillo.csc422midterm.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

@OptIn(InternalAPI::class)
class OnlineQuoteRepository(private val ktorClient: HttpClient = KtorClient()) : QuoteRepository {

    override suspend fun getQuotes(): List<Quote> {
        val response: QuoteResponse = ktorClient.request(HttpRoutes.QUOTES) {
            method = HttpMethod.Get
            url(HttpRoutes.QUOTES)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body()
        return response.quotes
    }

    override suspend fun getQuoteById(id: Int): Quote {
        val response: Quote = ktorClient.request("${HttpRoutes.QUOTES}/$id") {
            method = HttpMethod.Get
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body()
        return response
    }

    override suspend fun addQuote(quote: Quote): Quote {
        val response: Quote = ktorClient.request(HttpRoutes.QUOTES) {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            body = quote
        }.body()
        return response
    }

    override suspend fun updateQuote(quote: Quote): Quote {
        val response: Quote = ktorClient.request("${HttpRoutes.QUOTES}/${quote.id}") {
            method = HttpMethod.Put
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            body = quote
        }.body()
        return response
    }

    override suspend fun deleteQuote(id: Int): Boolean {
        val response: String = ktorClient.request("${HttpRoutes.QUOTES}/$id") {
            method = HttpMethod.Delete
        }.toString()
        return response == "OK"
    }
}
