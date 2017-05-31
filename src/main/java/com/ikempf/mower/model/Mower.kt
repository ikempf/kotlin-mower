package com.ikempf.mower.model

import com.ikempf.mower.model.Command.*
import com.ikempf.mower.model.Direction.Companion.left
import com.ikempf.mower.model.Direction.Companion.right

data class Mower(val pos: Position, val orientation: Direction) {

    fun executeCommand(command: Command): Mower =
        when (command) {
            ADVANCE -> {
                val newX = this.pos.x + this.orientation.xVect
                val newY = this.pos.y + this.orientation.yVect
                this.copy(pos = this.pos.copy(x = newX, y = newY))
            }
            TURN_RIGHT -> this.copy(orientation = this.orientation.right())
            TURN_LEFT -> this.copy(orientation = this.orientation.left())
        }

}