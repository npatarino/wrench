package me.nibbleapp.wrench.type

class Validation<out E : Any>(list: List<Either<E, *>>) {

    val failures: List<E> = list.filter { it.isLeft() }
            .map { it.swap().getOrNull() }
            .filterNotNull()

    val hasFailures: Boolean = failures.isNotEmpty()

    fun <F, S> execute(failure: () -> F,
                        handleValidationErrors: (E) -> Unit,
                        validationSuccess: () -> Unit,
                        success: () -> S) {
        if (hasFailures) {
            failure()
            failures.map {
                handleValidationErrors(it)
            }
        } else {
            validationSuccess()
            success()
        }
    }

}