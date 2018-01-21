package com.jmartinez.scala.cats.book.exercises

import org.scalatest.{Matchers, WordSpec}
import cats.syntax.functor._
import com.jmartinez.scala.cats.book.exercises.functorInstances._

class FunctorsSpec extends WordSpec with Matchers {

  "Tree Functor" should {
    "map a leaf properly" in {
      Tree.leaf(4).map(_ * 2) shouldBe Leaf(8)
    }
    "map a branch properly" in {
      Tree
        .branch(Leaf(4), Branch(Leaf(4), Leaf(4)))
        .map(identity) shouldBe Branch(Leaf(4), Branch(Leaf(4), Leaf(4)))
    }
  }

}
