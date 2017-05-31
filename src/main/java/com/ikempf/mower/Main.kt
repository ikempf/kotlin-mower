package com.ikempf.mower

import com.ikempf.mower.parsing.Parser
import com.ikempf.mower.reporting.Report.report
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {

    val sourceUrl = Thread.currentThread().contextClassLoader.getResource("mowing-job.txt")
    val sourceBytes = Files.readAllBytes(Paths.get(sourceUrl.toURI()))
    val source = String(sourceBytes)

    println("Running following job")
    println(source)
    val mowingJob = Parser.parse(source)
    val resultingMowers = mowingJob.execute()

    println("---------------------")
    println("Final mower positions")
    resultingMowers.forEach { m -> println(m.report()) }

}