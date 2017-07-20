package me.nibbleapp.wrench.sample.usecase

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.sample.error.SendEmailErrors
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation
import me.nibbleapp.wrench.usecase.UseCase
import me.nibbleapp.wrench.usecase.Validate

class EmailValidate(private val emails: List<String>, private val useCaseExecutor: UseCaseExecutor)
    : Validate<EmailErrors, SendEmailErrors, Boolean>() {

    private val validEmailPattern: Regex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
    private val maxLengthCharacters = 50
    private val minLengthCharacters = 6


    override val getValidation: Validation<EmailErrors>
        get() = Validation(emails.map { check(it) })

    override val useCase: UseCase<SendEmailErrors, Boolean>
        get() = object : UseCase<SendEmailErrors, Boolean>(useCaseExecutor) {
            override fun background(): Either<SendEmailErrors, Boolean> {
                return Either.Right(true)
            }
        }


    private fun check(email: String): Either<EmailErrors, Boolean> {
        return when {
            email.isBlank() -> Either.Left(EmailErrors.Empty(email))
            isTooLong(email) -> Either.Left(EmailErrors.TooLong(email))
            isTooShort(email) -> Either.Left(EmailErrors.TooShort(email))
            isInvalid(email) -> Either.Left(EmailErrors.Invalid(email))
            else -> Either.Right(true)
        }
    }

    private fun isInvalid(email: String) = !validEmailPattern.matches(email)

    private fun isTooShort(email: String) = email.length <= minLengthCharacters

    private fun isTooLong(email: String) = email.length > maxLengthCharacters
}
