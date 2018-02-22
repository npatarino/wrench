package com.idealista.android.wrench.usecase

import com.idealista.android.wrench.type.Either

class UseCase<Error, Result> {

    fun bg(background: () -> Either<Error, Result>, delay: Long = 0): Then<Error, Result> =
            Then(background, delay)

}

