package com.jmartinez.scala.cats.book.exercises

trait Monad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a => pure(func(a)))
}

object monadsInstances {

  type Id[A] = A

  val idMonad: Monad[Id] =
    new Monad[Id] {
      override def pure[A](a: A): Id[A] = a

      override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] =
        func(value)
    }

}
