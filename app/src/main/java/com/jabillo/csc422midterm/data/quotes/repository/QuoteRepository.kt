package com.jabillo.csc422midterm.data.quotes.repository

import com.jabillo.csc422midterm.data.quotes.model.Quote

interface QuoteRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuoteById(id: Int): Quote

    suspend fun addQuote(quote: Quote): Quote

    suspend fun updateQuote(quote: Quote): Quote

    suspend fun deleteQuote(id: Int): Boolean
}
