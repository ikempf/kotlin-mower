package com.ikempf.mower.model

import com.ikempf.mower.model.Command.*
import com.ikempf.mower.model.Direction.*
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class MowerTest : Spek({

    it("should advance") {
        // Given
        val mower = Mower(Position(1, 2), NORTH)

        // When
        val run = mower.executeCommand(ADVANCE)

        // Then
        run shouldEqual Mower(Position(1, 3), NORTH)
    }

    it("should turn left") {
        // Given
        val mower = Mower(Position(1, 2), NORTH)

        // When
        val run = mower.executeCommand(TURN_LEFT)

        // Then
        run shouldEqual Mower(Position(1, 2), WEST)
    }

    it("should turn right") {
        // Given
        val mower = Mower(Position(1, 2), NORTH)

        // When
        val run = mower.executeCommand(TURN_RIGHT)

        // Then
        run shouldEqual Mower(Position(1, 2), EAST)
    }

})