package me.nibbleapp.wrench.executor

import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.Then
import me.nibbleapp.wrench.usecase.UseCase
import org.junit.Test

class DefaultExecutorTest{


    @Test
    suspend fun `test`(){
        val deferred: Then<Any, Boolean> = UseCase<Any, Boolean>().bg({Either.Right(true)})
    }

}