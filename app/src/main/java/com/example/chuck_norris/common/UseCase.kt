package com.example.chuck_norris.common

interface UseCase<T, S> {
    suspend fun execute(params: S): T
}