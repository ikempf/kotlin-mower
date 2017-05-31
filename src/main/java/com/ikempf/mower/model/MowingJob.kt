package com.ikempf.mower.model

data class MowingJob(val mower: Mower, val commands: List<Command>) {

    fun execute(): Mower =
        commands.fold(mower, Mower::executeCommand)

}