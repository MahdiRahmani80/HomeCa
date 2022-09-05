package com.mehdi.rahmani.homeCa.data.repository

sealed class Resource<T> {

    class Success<T>(val data: T) : Resource<T>()
    class Failure<T>(val message: String) : Resource<T>()
}