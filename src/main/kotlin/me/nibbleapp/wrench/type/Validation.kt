package me.nibbleapp.wrench.type

sealed class Validation<out ValidationError> {

    object Valid : Validation<Nothing>() {
        override fun isValid(): Boolean = true
    }

    class Invalid<out ValidationError>(val error: ValidationError) : Validation<ValidationError>() {
        override fun isValid(): Boolean = false
    }

    abstract fun isValid(): Boolean

    inline fun <B> fold(fe: (ValidationError) -> B, fa: () -> B): B =
            when (this) {
                is Valid -> fa()
                is Invalid -> (fe(error))
            }

    fun toEither(): Either<ValidationError, Boolean> = fold({ Either.Left(it) }, { Either.Right(true) })
}