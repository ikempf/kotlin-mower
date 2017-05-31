package com.ikempf.mower.parsing

import com.ikempf.mower.model.*
import com.ikempf.mower.model.optics.Prisms
import me.sargunvohra.lib.cakeparse.api.*
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import org.funktionale.option.getOrElse

object Parser {

    val number = token("number", "[0-9]+")
    val direction = token("direction", "[NESW]")
    val command = token("command", "[AGD]")
    val newline = token("newline", "\n")
    val space = token("space", " ")

    val directionExpr = direction.map { dir ->
        Prisms.directionPrism.getOptional(dir.raw)
            .getOrElse { throw RuntimeException("String ${dir.raw} can't be parsed as direction") }
    }

    val numberExpr = number.map { num -> num.raw.toInt() }

    val commandExpr = command.map { cmd ->
        Prisms.commandPrism.getOptional(cmd.raw)
            .getOrElse { throw RuntimeException("String ${cmd.raw} can't be parsed as direction") }
    }

    val lawnExpr = (numberExpr before space and numberExpr)
        .map { (height, width) -> Lawn(height, width) }

    val positionExpr = (numberExpr before space and numberExpr)
        .map { (x, y) -> Position(x, y) }

    val mowerExpr = (positionExpr before space and directionExpr)
        .map { (position, direction) -> Mower(position, direction) }

    val mowingJobExpr = (mowerExpr before newline and oneOrMore(commandExpr))
        .map { (mower, commands) -> MowingJob(mower, commands) }

    val mowingBatchExpr = (lawnExpr before newline and
        (zeroOrMore(mowingJobExpr before newline) and mowingJobExpr))
        .map { (lawn, mowers) -> MowingBatch(lawn, mowers.first.plus(mowers.second)) }

    fun parse(input: String): MowingBatch =
        Lexer(setOf(number, direction, command, newline, space))
            .lex(input)
            .parseToEnd(mowingBatchExpr)
            .value

}