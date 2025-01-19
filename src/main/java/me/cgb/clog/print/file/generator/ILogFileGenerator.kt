package me.cgb.clog.print.file.generator

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/14
 * @desc
 * <p>
 * ================================================
 */
interface ILogFileGenerator {
    fun generateLogFile(folder: File): File
}