package com.jmartinez.scala.cats.book.exercises

import cats.instances.string._
import cats.syntax.semigroup._

object Main extends App {
  println("Hello " |+| "Cats!")
}
