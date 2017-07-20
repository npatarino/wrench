package me.nibbleapp.wrench.type

class Validation<out E : Any>(vararg disjunctionSequence: Either<E, *>) {

    val failures: List<E> = disjunctionSequence.filter { it.isLeft() }
            .map { it.swap().getOrNull() }
            .filterNotNull()

    val hasFailures: Boolean = failures.isNotEmpty()

    fun <F, S> validate(validationSuccess: () -> Unit, failure: (E) -> F, success: () -> S) {
        validationSuccess()
        if (hasFailures) {
            failures.map {
                failure(it)
            }
        } else {
            success()
        }
    }

}