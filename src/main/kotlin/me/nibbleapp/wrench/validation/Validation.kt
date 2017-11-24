package me.nibbleapp.wrench.validation

typealias Validations<E, V> = Validation<List<E>, V>

sealed class Validation<out L, out R> {

    data class Invalid<out T>(val value: T) : Validation<T, Nothing>()
    data class Valid<out T>(val value: T) : Validation<Nothing, T>()

    inline fun <C> fold(left: (L) -> C, right: (R) -> C): C = when (this) {
        is Invalid -> left(value)
        is Valid -> right(value)
    }

    fun swap() = fold({ Valid(it) }, { Invalid(it) })

    inline fun <C> map(f: (R) -> C): Validation<L, C> = fold({ Invalid(it) }, { Valid(f(it)) })

    fun getOrNull(): R? = fold({ null }, { it })

    fun <B> Validation<*, B>.getOrElse(default: () -> B): B = fold({ default() }, { it })

    fun <L, R, S> Validation<L, R>.flatMap(f: (R) -> Validation<L, S>): Validation<L, S> = when (this) {
        is Validation.Invalid -> Validation.Invalid(value)
        is Validation.Valid -> f(value)
    }

    companion object {
        fun <L, R> invalid(error: L): Validations<L, R> = Validation.Invalid(listOf(error))
    }

}

infix operator fun <E, V> Validations<E, V>.plus(other: Validations<E, V>): Validations<E, V> = when (this) {
    is Validation.Invalid -> other.fold({ Validation.Invalid(it + value) }, { Validation.Invalid(value) })
    is Validation.Valid -> other.fold({ Validation.Invalid(it) }, { Validation.Valid(it) })
}

infix operator fun <E, V> ((V) -> Validations<E, V>).plus(other: (V) -> Validations<E, V>): (V) -> Validations<E, V> = {
    this(it) + other(it)
}

