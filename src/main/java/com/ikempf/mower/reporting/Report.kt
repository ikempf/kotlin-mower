package com.ikempf.mower.reporting

import com.ikempf.mower.model.Mower
import com.ikempf.mower.model.optics.Prisms

object Report {

    fun Mower.report() =
        "${pos.x} ${pos.y} ${Prisms.directionPrism.reverseGet(orientation)}"

}