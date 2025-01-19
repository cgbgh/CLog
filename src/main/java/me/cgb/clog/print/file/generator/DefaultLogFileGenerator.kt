package me.cgb.clog.print.file.generator

import java.io.File

class DefaultLogFileGenerator : ILogFileGenerator {

    override fun generateLogFile(folder: File): File {
        return File(folder, "${System.currentTimeMillis()}.log")
    }

}
