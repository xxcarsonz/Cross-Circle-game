package com.testing

import scala.collection.mutable
import scala.io.StdIn

object CrossCircle {
	def main(args: Array[String]): Unit = {
		val checkerboard = new StringBuilder(
			"""[1][2][3]
				|[4][5][6]
				|[7][8][9]""".stripMargin)
		val indices = Array(1, 4, 7, 12, 15, 18, 23, 26, 29)
		val grids = new Array[Int](9)
		val map = new mutable.HashMap[Int, Int]

		(1 to 9).foreach(number => {
			map(number) = indices(number - 1)
		})

		(1 to 9).foreach(round => {
			println(checkerboard)
			if (round % 2 == 0) println("cross' turn") else println("circle's turn")
			var select = 0
			do {
				println("input the number you want to put")
				select = StdIn.readInt()
			} while (!(grids(select - 1) == 0 && select > 0 && select < 10))
			if (round % 2 != 0) {
				checkerboard.replace(map(select), map(select) + 1, "o")
				grids(select - 1) = 1
			} else {
				checkerboard.replace(map(select), map(select) + 1, "x")
				grids(select - 1) = 2
			}
			if (checkWinner(grids)) {
				println(checkerboard)
				System.exit(0)
			}
		})
		println("draw")
		println(checkerboard)
	}

	def checkWinner(grids: Array[Int]) = {
		val circleWin = List(1, 1, 1)
		val crossWin = List(2, 2, 2)
		val win1 = List(grids(0), grids(1), grids(2))
		val win2 = List(grids(3), grids(4), grids(5))
		val win3 = List(grids(6), grids(7), grids(8))
		val win4 = List(grids(0), grids(3), grids(6))
		val win5 = List(grids(1), grids(4), grids(7))
		val win6 = List(grids(2), grids(5), grids(8))
		val win7 = List(grids(0), grids(4), grids(8))
		val win8 = List(grids(2), grids(4), grids(6))
		val wins = List(win1, win2, win3, win4, win5, win6, win7, win8)
		var flag = true
		wins.foreach(win => {
			if (circleWin.equals(win)) {
				println("circle wins")
			} else if (crossWin.equals(win)) {
				println("cross wins")
			} else {
				flag = false
			}
		})
		flag
	}
}
