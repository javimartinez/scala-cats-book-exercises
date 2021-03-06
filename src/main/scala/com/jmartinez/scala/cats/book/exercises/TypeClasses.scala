package com.jmartinez.scala.cats.book.exercises

/**
  * Type Class definition
  *
     The Type classes provide a structured way to control ad hoc polymorphism most know as overloading
  *    ad hoc polymorphism: is a kind of polymorphism in which polymorphic functions can be applied to arguments of different types
  *
  *  A type class is an interface or API that represents some functionality we want to implement
  *
  *  Type Class examples: Semigroup, Monoid, Applicative, Functor, Monad ...
  *
  * In Scala/Cats type class is represented by a trait with at least one type parameter.
  */
trait Printable[A] {
  def format(value: A): String
}

/**
  * Interface object
  */
object Printable {

  def format[A](value: A)(implicit printable: Printable[A]): String =
    printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit =
    println(format(value))
}

/**
  * Interface syntax
  */
object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String =
      printable.format(value)
  }
}

final case class Cat(name: String, age: Int, color: String)

object PrintableInstances {

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      override def format(value: String): String = value
    }

  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      override def format(value: Int): String = value.toString
    }

  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      override def format(value: Cat): String =
        s"${value.name} is a ${value.age} year-old ${value.color} cat"
    }
}

/**
  * Implementation of the same functionality as above,
  * but using [cats.Show] type class
  */
object showTypeClassInstances {

  import cats.Show

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat")

}

/**
  *
  * Instances for [cats.Eq] Type class
  *
  */
object eqTypeClassInstances {

  import cats.Eq
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.eq._

  implicit val catEq: Eq[Cat] =
    Eq.instance { (cat1, cat2) =>
      cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2.color
    }
}
