package com.jmartinez.scala.cats.book.exercises

trait Printable2[A] { self =>
  def format(value: A): String

  def contramap[B](func: B => A): Printable2[B] =
    new Printable2[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}

object Printable2 {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)
}

final case class Box[A](value: A)

object printable2Instances {
  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable2[A]): Printable2[Box[A]] =
    p.contramap[Box[A]](box => box.value)
}
