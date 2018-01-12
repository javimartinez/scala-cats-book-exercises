package com.jmartinez.scala.cats.book.exercises

import org.scalatest.{Matchers, WordSpec}
import com.jmartinez.scala.cats.book.exercises.PrintableInstances._
import com.jmartinez.scala.cats.book.exercises.PrintableSyntax._

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
}
