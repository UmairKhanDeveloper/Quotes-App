package com.example.myapplication

interface ApiClient {
    suspend fun getAllQuotes():List<QuotesItem>
}