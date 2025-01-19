package me.cgb.clog.print.file.writer

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class DefaultFileWriter : IFileWriter {
    override var writingFile: File? = null
    private var bufferWriter: BufferedWriter? = null

    override fun appendLog(log: String) {
        try {
            bufferWriter?.append(log)
            bufferWriter?.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun isOpen(): Boolean {
        return bufferWriter != null && writingFile?.exists() == true
    }

    override fun openWriter(writingFile: File): Boolean {
        this.writingFile = writingFile
        try {
            bufferWriter = BufferedWriter(FileWriter(writingFile, true))
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun close() {
        try {
            bufferWriter?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            bufferWriter = null
            writingFile = null
        }
    }

}
