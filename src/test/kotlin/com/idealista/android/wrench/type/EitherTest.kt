package com.idealista.android.wrench.type

import com.idealista.android.wrench.type.Either.Left
import com.idealista.android.wrench.type.Either.Right
import org.junit.Assert
import org.junit.Test

class EitherTest {

    @Test
    fun `Should execute the success lambda in fold`() {
        val result = Right("Diego Armando Maradona").fold({ "Error" }, { "The best player is: $it" })
        Assert.assertEquals("The best player is: Diego Armando Maradona", result)
    }

    @Test
    fun `Should execute the error lambda in fold, check right execution should fail`() {
        val result = Left("Diego Armando Maradona").fold({ "Error" }, { "The best player is: $it" })
        Assert.assertNotEquals("The best player is: Diego Armando Maradona", result)
    }

    @Test
    fun `Should execute the error lambda in fold, check left execution`() {
        val result = Left("Diego Armando Maradona").fold({ "Error" }, { "The best player is: $it" })
        Assert.assertEquals("Error", result)
    }

    @Test
    fun `Should get a value`() {
        val value = Right("Diego Armando Maradona").getOrNull()
        Assert.assertEquals("Diego Armando Maradona", value)
    }

    @Test
    fun `Should get a null`() {
        val value = Left("Diego Armando Maradona").getOrNull()
        Assert.assertNull(value)
    }

    @Test
    fun `Should get a left null value`() {
        val either: Either<Nothing, String> = Right("Diego Armando Maradona")
        val value = either.swap().getOrNull()
        Assert.assertNull(value)
    }

    @Test
    fun `Should get a left value`() {
        val value = Left("Diego Armando Maradona").swap().getOrNull()
        Assert.assertEquals("Diego Armando Maradona", value)
    }

    @Test
    fun `Should map right bias and return a right Either`() {
        val value = Right("Diego Armando Maradona").bimap({ "Error"}, { "Success" })
        Assert.assertEquals(Right("Success"), value)
        Assert.assertTrue(value.isRight())
    }

    @Test
    fun `Should map left bias and return a left Either`() {
        val value = Left("Diego Armando Maradona").bimap({ "Error"}, { "Success" })
        Assert.assertEquals(Left("Error"), value)
        Assert.assertTrue(value.isLeft())
    }

    @Test
    fun `flatMap should return Left if Right and Left are used`() {
        val value = Right("Diego Armando Maradona").flatMap {
            Left("Error")
        }
        Assert.assertEquals(Left("Error"), value)
        Assert.assertTrue(value.isLeft())
    }

    @Test
    fun `flatMap should return Right if Right and Right are used`() {
        val value = Right("Diego Armando Maradona").flatMap {
            Right("Error")
        }
        Assert.assertEquals(Right("Error"), value)
        Assert.assertTrue(value.isRight())
    }

}
