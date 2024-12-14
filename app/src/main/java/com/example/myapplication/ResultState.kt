package com.example.myapplication

import coil.compose.AsyncImagePainter

sealed class ResultState<out T> {
    object Laoding : ResultState<Nothing>()
    data class Success<T>(val response: T) : ResultState<T>()
    data class Error(val error: Throwable) : ResultState<Nothing>()
}