package com.example.myapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuotesItem(
    @SerialName("author")
    val author: String,
    @SerialName("quote")
    val quote: String,
)