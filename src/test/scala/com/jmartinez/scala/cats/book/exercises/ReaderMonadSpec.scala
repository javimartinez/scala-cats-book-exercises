package com.jmartinez.scala.cats.book.exercises

import org.scalatest.{Matchers, WordSpec}
import ReaderMonad._

class ReaderMonadSpec extends WordSpec with Matchers {

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

  checkLogin(1, "zerocool").run(db) shouldBe true

  checkLogin(4, "davinci").run(db) shouldBe false

}
