package me.cgb.clog.print.file.generator

import me.cgb.clog.CLog
import java.io.File

class GlobalTagFileGenerator : ILogFileGenerator {

    override fun generateLogFile(folder: File): File {
        return File(folder, "${CLog.config.globalTag}.log")
    }

}
