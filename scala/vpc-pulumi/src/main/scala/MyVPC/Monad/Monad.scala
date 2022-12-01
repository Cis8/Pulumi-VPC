package MyVPC.MonadPkg

import MyVPC.FunctorPkg.Functor

trait Monad[F[_]] extends Functor[F]:

  /** The unit value for a monad */
  def pure[A](x: A): F[A]

  extension [A](x: F[A])
    /** The fundamental composition operation */
    def flatMap[B](f: A => F[B]): F[B]

    /** The `map` operation can now be defined in terms of `flatMap` */
    def map[B](f: A => B) = x.flatMap(f.andThen(pure))

end Monad