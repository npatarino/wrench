package com.idealista.android.wrench.type

sealed class Option<out S> {

    data class Some<out S>(val value: S) : Option<S>()

    object None : Option<Nothing>()

    inline fun <C> fold(ifEmpty: () -> C, some: (S) -> C): C = when (this) {
        None -> ifEmpty()
        is Some -> some(value)
    }

    inline fun <C> map(f: (S) -> C): Option<C> = fold({ None }, { Some(f(it)) })

    fun getOrNull(): S? = fold({ null }, { it })

}

fun Any.pure() : Option<Any> = Option.Some(this)

fun Any?.toOption() : Option<Any> = if (this == null) Option.None else Option.Some(this)

fun <B> Option<B>.getOrElse(default: () -> B): B = fold({ default() }, { it })

fun <R, S> Option<R>.flatMap(f: (R) -> Option<S>): Option<S> = when (this) {
    Option.None -> Option.None
    is Option.Some -> f(value)
}
