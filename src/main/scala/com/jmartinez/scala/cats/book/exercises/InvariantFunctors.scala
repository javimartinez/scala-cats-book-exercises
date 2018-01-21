package com.jmartinez.scala.cats.book.exercises

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this
    new Codec[B] {
      override def encode(value: B): String =
        self.encode(enc(value))
      override def decode(value: String): B =
        dec(self.decode(value))
    }
  }

}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)
}

case class Box2[A](value: A)

object codecInstances {

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  implicit val doubleCodec: Codec[Double] =
    stringCodec.imap(_.toDouble, _.toString)

  implicit def box2Codec[A](implicit codec: Codec[A]): Codec[Box2[A]] =
    codec.imap[Box2[A]](Box2.apply, _.value)
}
