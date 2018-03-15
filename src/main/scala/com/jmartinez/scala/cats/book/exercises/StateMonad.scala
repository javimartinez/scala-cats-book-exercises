package com.jmartinez.scala.cats.book.exercises

import cats.data.State
import cats.syntax.applicative._ // for pure

object StateMonad {

  type CalcState[A] = State[List[Int], A]

  def operand(num: Int): State[List[Int], Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def operator(f: (Int, Int) => Int): State[List[Int], Int] =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val result = f(a, b)
        (result :: tail, result)
      case _ =>
        sys.error("Fail!")
    }

  def evalOne(sym: String): CalcState[Int] = {
    sym match {
      case "+" =>
        operator(_ + _)
      case "-" =>
        operator(_ - _)
      case "*" =>
        operator(_ * _)
      case "%" =>
        operator(_ / _)
      case num =>
        operand(num.toInt)
    }
  }

  def evalAll(input: List[String]): CalcState[Int] = {
    input.foldLeft(0.pure[CalcState]) { (s, element) =>
      s.flatMap(_ => evalOne(element))
    }
  }

  def evalInput(input: String): Int = {
    evalAll(input.split(" ").toList).runA(Nil).value
  }

}
