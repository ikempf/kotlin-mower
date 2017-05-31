package com.ikempf.mower.parsing

import com.ikempf.mower.model.*
import com.ikempf.mower.model.Command.*
import com.ikempf.mower.model.Direction.*
import com.ikempf.mower.parsing.Parser.command
import com.ikempf.mower.parsing.Parser.commandExpr
import com.ikempf.mower.parsing.Parser.direction
import com.ikempf.mower.parsing.Parser.directionExpr
import com.ikempf.mower.parsing.Parser.lawnExpr
import com.ikempf.mower.parsing.Parser.mowerExpr
import com.ikempf.mower.parsing.Parser.newline
import com.ikempf.mower.parsing.Parser.number
import com.ikempf.mower.parsing.Parser.space
import com.ikempf.mower.parsing.Parser.mowingBatchExpr
import com.ikempf.mower.parsing.Parser.mowingJobExpr
import me.sargunvohra.lib.cakeparse.api.parseToEnd
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class DirectionTest : Spek({

    it("should parse a lawn") {
        // Given
        val input = "3 4"

        // When
        val result = Lexer(setOf(number, space)).lex(input).parseToEnd(lawnExpr)

        // Then
        result.value shouldEqual Lawn(3, 4)
    }

    it("should parse a direction") {
        // Given
        val input = "E"

        // When
        val result = Lexer(setOf(direction)).lex(input).parseToEnd(directionExpr)

        // Then
        result.value shouldEqual EAST
    }

    it("should parse a command") {
        // Given
        val input = "D"

        // When
        val result = Lexer(setOf(command)).lex(input).parseToEnd(commandExpr)

        // Then
        result.value shouldEqual TURN_RIGHT
    }

    it("should parse a mower") {
        // Given
        val input = "2 1 W"

        // When
        val result = Lexer(setOf(number, direction, space, newline, command))
            .lex(input)
            .parseToEnd(mowerExpr)

        // Then
        result.value shouldEqual Mower(Position(2, 1), WEST)
    }

    it("should parse a mowing job") {
        // Given
        val input =
            "2 1 W\n" +
                "AAGADA"

        // When
        val result = Lexer(setOf(number, direction, space, newline, command))
            .lex(input)
            .parseToEnd(mowingJobExpr)

        // Then
        val mower = Mower(Position(2, 1), WEST)
        result.value shouldEqual MowingJob(mower, listOf(ADVANCE, ADVANCE, TURN_LEFT, ADVANCE, TURN_RIGHT, ADVANCE))
    }

    it("should parse a specification") {
        // Given
        val input =
            "4 8\n" +
                "2 1 W\n" +
                "AAGADA\n" +
                "3 4 N\n" +
                "GA"

        // When
        val result = Lexer(setOf(number, direction, space, newline, command))
            .lex(input)
            .parseToEnd(mowingBatchExpr)

        // Then
        val mower1 = Mower(Position(2, 1), WEST)
        val mower2 = Mower(Position(3, 4), NORTH)
        val lawn = Lawn(4, 8)
        val mowingJob1 = MowingJob(mower1, listOf(ADVANCE, ADVANCE, TURN_LEFT, ADVANCE, TURN_RIGHT, ADVANCE))
        val mowingJob2 = MowingJob(mower2, listOf(TURN_LEFT, ADVANCE))
        result.value shouldEqual MowingBatch(lawn, listOf(mowingJob1, mowingJob2))
    }

})