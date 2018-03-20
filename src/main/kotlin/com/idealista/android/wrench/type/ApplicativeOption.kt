package com.idealista.android.wrench.type

class ApplicativeOption {

    companion object {
        fun <A, B> product(first: Option<A>, second: Option<B>): Option<Tuple2<A, B>> = first.flatMap { first ->
            second.map {
                Tuple2(first, it)
            }
        }

        fun <A, B, C> product(first: Option<A>, second: Option<B>, third: Option<C>): Option<Tuple3<A, B, C>> =
                product(first, second).flatMap { product ->
                    third.map {
                        Tuple3(product.first, product.second, it)
                    }
                }

        fun <A, B, C, D> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>): Option<Tuple4<A, B, C, D>> =
                product(first, second, third).flatMap { product ->
                    fourth.map {
                        Tuple4(product.first, product.second, product.third, it)
                    }
                }

        fun <A, B, C, D, E> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>): Option<Tuple5<A, B, C, D, E>> =
                product(first, second, third, fourth).flatMap { product ->
                    fifth.map {
                        Tuple5(product.first, product.second, product.third, product.fourth, it)
                    }
                }

        fun <A, B, R> map(first: Option<A>, second: Option<B>, some: (Tuple2<A, B>) -> R): Option<R> =
                product(first, second).map(some)

        fun <A, B, C, R> map(first: Option<A>, second: Option<B>, third: Option<C>, some: (Tuple3<A, B, C>) -> R): Option<R> =
                product(first, second, third).map(some)

        fun <A, B, C, D, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, some: (Tuple4<A, B, C, D>) -> R): Option<R> =
                product(first, second, third, fourth).map(some)

        fun <A, B, C, D, E, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, some: (Tuple5<A, B, C, D, E>) -> R): Option<R> =
                product(first, second, third, fourth, fifth).map(some)
    }
}