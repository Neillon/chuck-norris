package com.example.chuck_norris.network.abstractions

sealed class Either<out E, out V> {
    data class Left<out L>(val packet: L) : Either<L, Nothing>()
    data class Right<out R>(val packet: R) : Either<Nothing, R>()

    companion object {
        fun <R> right(value: R): Either<Nothing, R> =
            Either.Right(value)

        fun <L> left(value: L): Either<L, Nothing> =
            Either.Left(value)
    }
}