package com.ikempf.mower.reporting

import com.ikempf.mower.model.Direction.EAST
import com.ikempf.mower.model.Mower
import com.ikempf.mower.model.Position
import com.ikempf.mower.reporting.Report.report
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ReportTest : Spek({

    it("should report mowers state", {
        // Given
        val mower = Mower(Position(4, 7), EAST)

        // When
        val report = mower.report()

        // Then
        report shouldEqual "4 7 E"
    })

})