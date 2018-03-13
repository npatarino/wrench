package com.idealista.android.wrench.type

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ApplicativeOptionTest {

    @Test fun `given two somes, applicative should call the lambda and return a some`() {
        val option1 : Option<Int> = Option.Some(1)
        val sum = ApplicativeOption().map(option1, Option.Some(2)) { (first, second) ->
            first + second
        }

        assertEquals(3, sum.getOrElse { 0 })
    }

    @Test fun `given one some and none, applicative should not call the lambda and return a none`() {
        val option1 : Option<Int> = Option.None
        val sum = ApplicativeOption().map(option1, Option.Some(2)) { (first, second) ->
            first + second
        }

        assertTrue(sum === Option.None)
    }

    @Test fun `given three somes, applicative should call the lambda and return a some`() {
        val option1 : Option<Int> = Option.Some(1)
        val sum = ApplicativeOption().map(option1, Option.Some(2), Option.Some(2)) { (first, second, three) ->
            first + second + three
        }

        assertEquals(5, sum.getOrElse { 0 })
    }

    @Test fun `given two somes and none, applicative should not call the lambda and return a none`() {
        val option1 : Option<Int> = Option.None
        val sum = ApplicativeOption().map(option1, Option.Some(2), Option.Some(2)) { (first, second) ->
            first + second
        }

        assertTrue(sum === Option.None)
    }
}