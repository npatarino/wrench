package me.nibbleapp.wrench.type

sealed class Either<out L, out R> {

  data class Left<out T>(val value: T) : Either<T, Nothing>()
  data class Right<out T>(val value: T) : Either<Nothing, T>()

  fun fold(left: (L) -> Unit, right: (R) -> Unit): Unit = when (this) {
    is Left  -> left(value)
    is Right -> right(value)
  }

  fun swap() = fold({ Right(it) }, { Left(it) })

}


