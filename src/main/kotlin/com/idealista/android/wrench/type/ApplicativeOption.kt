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

        fun <A, B, C, D, E, F> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>): Option<Tuple6<A, B, C, D, E, F>> =
                product(first, second, third, fourth, fifth).flatMap { product ->
                    sixth.map {
                        Tuple6(product.first, product.second, product.third, product.fourth, product.fifth, it)
                    }
                }

        fun <A, B, C, D, E, F, G> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>): Option<Tuple7<A, B, C, D, E, F, G>> =
                product(first, second, third, fourth, fifth, sixth).flatMap { product ->
                    seventh.map {
                        Tuple7(product.first, product.second, product.third, product.fourth, product.fifth, product.sixth, it)
                    }
                }

        fun <A, B, C, D, E, F, G, H> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>): Option<Tuple8<A, B, C, D, E, F, G, H>> =
                product(first, second, third, fourth, fifth, sixth, seventh).flatMap { product ->
                    eighth.map {
                        Tuple8(product.first, product.second, product.third, product.fourth, product.fifth, product.sixth, product.seventh, it)
                    }
                }

        fun <A, B, C, D, E, F, G, H, I> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>, nineth: Option<I>): Option<Tuple9<A, B, C, D, E, F, G, H, I>> =
                product(first, second, third, fourth, fifth, sixth, seventh, eighth).flatMap { product ->
                    nineth.map {
                        Tuple9(product.first, product.second, product.third, product.fourth, product.fifth, product.sixth, product.seventh, product.eighth, it)
                    }
                }

        fun <A, B, C, D, E, F, G, H, I, J> product(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>, nineth: Option<I>, tenth: Option<J>): Option<Tuple10<A, B, C, D, E, F, G, H, I, J>> =
                product(first, second, third, fourth, fifth, sixth, seventh, eighth, nineth).flatMap { product ->
                    tenth.map {
                        Tuple10(product.first, product.second, product.third, product.fourth, product.fifth, product.sixth, product.seventh, product.eighth, product.nineth, it)
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

        fun <A, B, C, D, E, F, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, some: (Tuple6<A, B, C, D, E, F>) -> R): Option<R> =
                product(first, second, third, fourth, fifth, sixth).map(some)

        fun <A, B, C, D, E, F, G, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, some: (Tuple7<A, B, C, D, E, F, G>) -> R): Option<R> =
                product(first, second, third, fourth, fifth, sixth, seventh).map(some)

        fun <A, B, C, D, E, F, G, H, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>, some: (Tuple8<A, B, C, D, E, F, G, H>) -> R): Option<R> =
                product(first, second, third, fourth, fifth, sixth, seventh, eighth).map(some)

        fun <A, B, C, D, E, F, G, H, I, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>, nineth: Option<I>, some: (Tuple9<A, B, C, D, E, F, G, H, I>) -> R): Option<R> =
                product(first, second, third, fourth, fifth, sixth, seventh, eighth, nineth).map(some)

        fun <A, B, C, D, E, F, G, H, I, J, R> map(first: Option<A>, second: Option<B>, third: Option<C>, fourth: Option<D>, fifth: Option<E>, sixth: Option<F>, seventh: Option<G>, eighth: Option<H>, nineth: Option<I>, tenth: Option<J>, some: (Tuple10<A, B, C, D, E, F, G, H, I, J>) -> R): Option<R> =
                product(first, second, third, fourth, fifth, sixth, seventh, eighth, nineth, tenth).map(some)
    }
}