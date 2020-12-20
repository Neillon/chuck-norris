package com.example.chuck_norris.abstractions

interface UseCase<T, S> {
    suspend fun execute(params: S): T
}