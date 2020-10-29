package com.example.friendscircle.ext

sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class DoSth<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) {
    when {
        true -> DoSth(block())
        else -> Otherwise
    }
}

inline fun <T> Boolean.no(block: () -> T) = when {
    this -> Otherwise
    else -> {
        DoSth(block())
    }
}


inline fun <T> BooleanExt<T>.otherwise(block: () -> T) {
    when (this) {
        is Otherwise -> block()
        is DoSth -> this.data
    }
}


