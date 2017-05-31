package com.ikempf.mower.model

data class MowingBatch(val lawn: Lawn, val jobs: List<MowingJob>) {

    fun execute(): List<Mower> =
        jobs.map(MowingJob::execute)

}