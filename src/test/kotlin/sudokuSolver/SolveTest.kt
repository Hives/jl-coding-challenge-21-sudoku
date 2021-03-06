package sudokuSolver

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SolveTest : Spek({
    describe("Solver") {
        it("gives the right answer for a board that can be solved with multiple iterations of the deducer") {
            val board = Board(
                listOf(
                    7, 0, 9, 0, 0, 2, 6, 8, 0,
                    0, 0, 2, 0, 5, 0, 7, 0, 4,
                    0, 0, 0, 0, 0, 0, 2, 0, 0,
                    1, 9, 0, 0, 0, 7, 0, 6, 0,
                    8, 6, 7, 1, 9, 5, 0, 4, 0,
                    5, 0, 4, 0, 0, 0, 0, 9, 0,
                    4, 3, 5, 7, 8, 0, 0, 2, 0,
                    0, 0, 6, 4, 0, 0, 0, 0, 1,
                    9, 8, 0, 5, 0, 6, 0, 0, 3
                )
            )
            val solution = Board(
                listOf(
                    7, 4, 9, 3, 1, 2, 6, 8, 5,
                    6, 1, 2, 9, 5, 8, 7, 3, 4,
                    3, 5, 8, 6, 7, 4, 2, 1, 9,
                    1, 9, 3, 2, 4, 7, 5, 6, 8,
                    8, 6, 7, 1, 9, 5, 3, 4, 2,
                    5, 2, 4, 8, 6, 3, 1, 9, 7,
                    4, 3, 5, 7, 8, 1, 9, 2, 6,
                    2, 7, 6, 4, 3, 9, 8, 5, 1,
                    9, 8, 1, 5, 2, 6, 4, 7, 3
                )
            )
            assertThat(solve(board)).isEqualTo(solution)
        }

        it("gives a solution for a board that can't be solved just with the deducer") {
            val board = Board(
                listOf(
                    0, 0, 0, 0, 7, 4, 3, 1, 6,
                    0, 0, 0, 6, 0, 3, 8, 4, 0,
                    0, 0, 0, 0, 0, 8, 5, 0, 0,
                    7, 2, 5, 8, 0, 0, 0, 3, 4,
                    0, 0, 0, 0, 3, 0, 0, 5, 0,
                    0, 0, 0, 0, 0, 2, 7, 9, 8,
                    0, 0, 8, 9, 4, 0, 0, 0, 0,
                    0, 4, 0, 0, 8, 5, 9, 0, 0,
                    9, 7, 1, 3, 2, 6, 4, 8, 5
                )
            )
            val solution = Board(
                listOf(
                    5, 8, 9, 2, 7, 4, 3, 1, 6,
                    2, 1, 7, 6, 5, 3, 8, 4, 9,
                    4, 6, 3, 1, 9, 8, 5, 2, 7,
                    7, 2, 5, 8, 1, 9, 6, 3, 4,
                    8, 9, 6, 4, 3, 7, 1, 5, 2,
                    1, 3, 4, 5, 6, 2, 7, 9, 8,
                    6, 5, 8, 9, 4, 1, 2, 7, 3,
                    3, 4, 2, 7, 8, 5, 9, 6, 1,
                    9, 7, 1, 3, 2, 6, 4, 8, 5
                )
            )
            assertThat(solve(board)).isEqualTo(solution)
        }

        it("here's a board with more than one solution") {
            val board = Board(
                listOf(
                    0, 8, 0, 0, 0, 9, 7, 4, 3,
                    0, 5, 0, 0, 0, 8, 0, 1, 0,
                    0, 1, 0, 0, 0, 0, 0, 0, 0,
                    8, 0, 0, 0, 0, 5, 0, 0, 0,
                    0, 0, 0, 8, 0, 4, 0, 0, 0,
                    0, 0, 0, 3, 0, 0, 0, 0, 6,
                    0, 0, 0, 0, 0, 0, 0, 7, 0,
                    0, 3, 0, 5, 0, 0, 0, 8, 0,
                    9, 7, 2, 4, 0, 0, 0, 5, 0
                )
            )
            assertThat(solve(board).isSolution()).isTrue()
        }

        it("it throws an error if no solution exists") {
            val board = Board(
                listOf(
                    7, 8, 1, 5, 4, 3, 9, 2, 6,
                    0, 0, 6, 1, 7, 9, 5, 0, 0,
                    9, 5, 4, 6, 2, 8, 7, 3, 1,
                    6, 9, 5, 8, 3, 7, 2, 1, 4,
                    1, 4, 8, 2, 6, 5, 3, 7, 9,
                    3, 2, 7, 9, 1, 4, 8, 0, 0,
                    4, 1, 3, 7, 5, 2, 6, 9, 8,
                    0, 0, 2, 0, 0, 0, 4, 0, 0,
                    5, 7, 9, 4, 8, 6, 1, 0, 3
                )
            )
            assertThat { solve(board) }.isFailure()
        }
    }
})