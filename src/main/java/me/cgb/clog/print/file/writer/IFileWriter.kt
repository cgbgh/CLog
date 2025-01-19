package me.cgb.clog.print.file.writer

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/14
 * @desc
 * <p>
 * ================================================
 */
interface IFileWriter {
    var writingFile: File?

    fun appendLog(log: String)

    fun isOpen(): Boolean

    fun openWriter(writingFile: File): Boolean

    fun close()
}