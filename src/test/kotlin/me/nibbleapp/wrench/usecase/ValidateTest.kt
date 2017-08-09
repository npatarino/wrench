package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.sample.executor.DefaultExecutor
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.Executors

class ValidateTest {

    private val mainExecutor = DefaultExecutor(Executors.newScheduledThreadPool(4))

    private val oneValidValidation = listOf({ Validation.Valid })
    private val oneInvalidValidation = listOf({ Validation.Invalid(UnknownError()) })
    private val twoInvalidsValidation = listOf({ Validation.Invalid(UnknownError()) }, { Validation.Invalid(UnknownError()) })
    private val twoValidValidation = listOf({ Validation.Valid }, { Validation.Valid })
    private val validAndInvalidValidation = listOf({ Validation.Invalid(UnknownError()) }, { Validation.Valid })

//    @Test
//    fun `Given empty list, validation should execute the use case`() {
//        val validated = Validate(emptyList(), UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        Assert.assertTrue(validated.isRight())
//    }
//
//    @Test
//    fun `Given not valid validation list, validation should not execute the use case`() {
//        val validated = Validate(oneInvalidValidation, UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        Assert.assertTrue(validated.isLeft())
//    }
//
//    @Test
//    fun `Given valid validation list, validation should execute the use case`() {
//        val validated = Validate(oneValidValidation, UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        Assert.assertTrue(validated.isRight())
//    }
//
//    @Test
//    fun `Given two not valid validations, validation errors should be two`() {
//        val validated = Validate(twoInvalidsValidation, UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        validated.fold({ Assert.assertEquals(2, it.size)}, {})
//    }
//
//    @Test
//    fun `Given two valid validations, validation errors should be zero`() {
//        val validated = Validate(twoValidValidation, UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        validated.fold({ Assert.assertEquals(0, it.size)}, {})
//    }
//
//    @Test
//    fun `Given valids and invalid validations, validation should not execute the use case`() {
//        val validated = Validate(validAndInvalidValidation, UseCase({ Either.Right(true) }, mainExecutor)).validate()
//        Assert.assertTrue(validated.isLeft())
//    }

}