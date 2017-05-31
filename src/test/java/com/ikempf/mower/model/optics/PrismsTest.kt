package com.ikempf.mower.model.optics

import com.ikempf.mower.model.Command.*
import com.ikempf.mower.model.Direction.*
import com.ikempf.mower.model.optics.Prisms.commandPrism
import com.ikempf.mower.model.optics.Prisms.directionPrism
import org.amshove.kluent.shouldEqual
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some
import org.jetbrains.spek.api.dsl.it


class DirectionTest : org.jetbrains.spek.api.Spek({

    it("should get direction from string") {
        directionPrism.getOptional("N") shouldEqual Some(NORTH)
        directionPrism.getOptional("E") shouldEqual Some(EAST)
        directionPrism.getOptional("S") shouldEqual Some(SOUTH)
        directionPrism.getOptional("W") shouldEqual Some(WEST)
        directionPrism.getOptional("X") shouldEqual None
    }

    it("should get string from direction") {
        directionPrism.reverseGet(NORTH) shouldEqual "N"
        directionPrism.reverseGet(EAST) shouldEqual "E"
        directionPrism.reverseGet(SOUTH) shouldEqual "S"
        directionPrism.reverseGet(WEST) shouldEqual "W"
    }

    it("should get command from string") {
        commandPrism.getOptional("A") shouldEqual Some(ADVANCE)
        commandPrism.getOptional("G") shouldEqual Some(TURN_LEFT)
        commandPrism.getOptional("D") shouldEqual Some(TURN_RIGHT)
        commandPrism.getOptional("X") shouldEqual None
    }

    it("should get string from command") {
        commandPrism.reverseGet(ADVANCE) shouldEqual "A"
        commandPrism.reverseGet(TURN_LEFT) shouldEqual "G"
        commandPrism.reverseGet(TURN_RIGHT) shouldEqual "D"
    }

})