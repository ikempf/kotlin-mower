package com.ikempf.mower.model.optics

import com.ikempf.mower.model.Command
import com.ikempf.mower.model.Command.*
import com.ikempf.mower.model.Direction
import com.ikempf.mower.model.Direction.*
import com.ikempf.optics.Prism
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

object Prisms {

    val directionPrism = Prism<String, Direction>(
        { str ->
            when (str) {
                "N" -> Some(NORTH)
                "E" -> Some(EAST)
                "S" -> Some(SOUTH)
                "W" -> Some(WEST)
                else -> None
            }
        },
        { direction ->
            when (direction) {
                NORTH -> "N"
                EAST -> "E"
                SOUTH -> "S"
                WEST -> "W"
            }
        })

    val commandPrism = Prism<String, Command>(
        { str ->
            when (str) {
                "A" -> Some(ADVANCE)
                "D" -> Some(TURN_RIGHT)
                "G" -> Some(TURN_LEFT)
                else -> None
            }
        },
        { command ->
            when (command) {
                ADVANCE -> "A"
                TURN_RIGHT -> "D"
                TURN_LEFT -> "G"
            }
        })

}