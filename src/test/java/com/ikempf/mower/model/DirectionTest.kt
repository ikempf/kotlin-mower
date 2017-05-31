package com.ikempf.mower.model

import com.ikempf.mower.model.Direction.*
import com.ikempf.mower.model.Direction.Companion.right
import com.ikempf.mower.model.Direction.Companion.left
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.dsl.it

class DirectionTest : org.jetbrains.spek.api.Spek({

    it("should return the direction to the right") {
        NORTH.right() shouldEqual EAST
        EAST.right() shouldEqual SOUTH
        SOUTH.right() shouldEqual WEST
        WEST.right() shouldEqual NORTH
    }

    it("should return the direction to the left") {
        NORTH.left() shouldEqual WEST
        WEST.left() shouldEqual SOUTH
        SOUTH.left() shouldEqual EAST
        EAST.left() shouldEqual NORTH
    }

})