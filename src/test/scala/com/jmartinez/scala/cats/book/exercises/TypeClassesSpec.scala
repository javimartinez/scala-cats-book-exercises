package com.jmartinez.scala.cats.book.exercises

import cats.kernel.Eq
import cats.syntax.show._
import cats.syntax.eq._
import com.jmartinez.scala.cats.book.exercises.PrintableInstances._
import com.jmartinez.scala.cats.book.exercises.PrintableSyntax._
import com.jmartinez.scala.cats.book.exercises.eqTypeClassInstances._
import com.jmartinez.scala.cats.book.exercises.showTypeClassInstances._
import org.scalatest.{Matchers, WordSpec}

/**
  * Simple testing to show how to use Printable type class
  *
  */
class TypeClassesSpec extends WordSpec with Matchers {

  val jasperCat = Cat("Jasper", 10, "black")

  val stringRepresentationJasperCat: String =
    "Jasper is a 10 year-old black cat"

  "Printable type class" should {

    "get string representation of a cat using interface object" in {
      Printable.format(jasperCat) shouldBe stringRepresentationJasperCat
    }

    "get string representation of a cat using interface syntax" in {
      jasperCat.format shouldBe stringRepresentationJasperCat
    }
  }

  "Show type class" should {
    "get string representation of a cat" in {
      jasperCat.show shouldBe stringRepresentationJasperCat
    }
  }

  "Eq type class" should {
    "get true when the cats are the same, using eqv operator" in {
      val jasperCat2 = Cat("Jasper", 10, "black")
      Eq.eqv(jasperCat, jasperCat2) shouldBe true // not using === for ambiguity
    }

    "get false when the cats are different, using eqv operator" in {
      val bellaCat = Cat("Bella", 10, "black")
      Eq.eqv(jasperCat, bellaCat) shouldBe false // not using === for ambiguity
    }

    "get false when the cats are the same, using =!= operator" in {
      jasperCat =!= jasperCat shouldBe false
    }
  }
}
