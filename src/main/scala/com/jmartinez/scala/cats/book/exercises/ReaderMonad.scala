package com.jmartinez.scala.cats.book.exercises

import cats.data.Reader
import cats.syntax.applicative._ // for pure

case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
)

object ReaderMonad {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(
      username: String,
      password: String
  ): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(
      userId: Int,
      password: String
  ): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      check <- username
        .map { name =>
          checkPassword(name, password)
        }
        .getOrElse(false.pure[DbReader])
    } yield check

}
