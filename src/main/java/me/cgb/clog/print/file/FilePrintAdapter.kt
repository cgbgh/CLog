package me.cgb.clog.print.file

import me.cgb.clog.internal.Defaults
import me.cgb.clog.print.AbstractTypesettingPrintAdapter
import me.cgb.clog.print.file.clean.ICleanStrategy
import me.cgb.clog.print.file.generator.ILogFileGenerator
import me.cgb.clog.print.file.header.ILogHeader
import me.cgb.clog.print.file.storage.IStorageStrategy
import me.cgb.clog.print.file.writer.IFileWriter
import me.cgb.clog.print.typeset.ITypesetter
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue


/**
 * ================================================
 * @author cgb
 * @date 2025/1/13
 * @desc
 * <p>
 * ================================================
 */
class FilePrintAdapter(builder: Builder) : AbstractTypesettingPrintAdapter() {
    private val writer: IFileWriter = builder.writer ?: Defaults.defaultFileWriter()
    private val storageStrategy: IStorageStrategy =
        builder.storageStrategy ?: Defaults.defaultStorageStrategy()
    private val logFileGenerator: ILogFileGenerator =
        builder.logFileGenerator ?: Defaults.defaultLogFileGenerator()
    private val cleanStrategy: ICleanStrategy =
        builder.cleanStrategy ?: Defaults.defaultCleanStrategy()
    private val logFolder: String = builder.logFolder
    override var typesetter: ITypesetter? = builder.typesetter ?: Defaults.defaultTypesetter()
    private val header: ILogHeader = builder.header ?: Defaults.defaultFileHeader()
    private val executor: ExecutorService = builder.executor ?: Defaults.defaultFilePrintExecutor()
    private val worker = Worker()

    override fun println(content: String) {
        if (!worker.isStarted) {
            executor.execute(worker)
        }
        worker.enqueue(content)
    }

    private fun doPrintln(content: String) {
        var writingFile = writer.writingFile
        val isClosed = !writer.isOpen()
        val folder = File(logFolder)
        if (writingFile == null || isClosed) {
            cleanLogIfNeeded()
            writingFile = generateWritingFile(folder)
        }
        if (storageStrategy.isReachesLimit(writingFile)) {
            writer.close()
            transfer(writingFile)
            cleanLogIfNeeded()
            generateWritingFile(folder)
        }
        if (!writer.openWriter(writingFile)) {
            onError("can not open writer")
            return
        }
        if (writingFile.length() <= 0) {
            writer.appendLog(header.createHeader())
        }
        writer.appendLog(content)
    }

    private fun transfer(file: File) {
        for (i in 0 until Int.MAX_VALUE) {
            val transferFile = File(file.name + ".$i")
            if (!transferFile.exists()) {
                file.renameTo(transferFile)
            }

        }
    }

    private fun onError(msg: String) {
        System.out.println(msg)
    }

    private fun cleanLogIfNeeded() {
        val folder = File(logFolder)
        val logs = folder.listFiles() ?: return
        for (i in logs.indices) {
            val log = logs[i]
            if (cleanStrategy.shouldClean(log)) {
                log.delete()
            }
        }
    }

    private fun generateWritingFile(folder: File): File {
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val logFile = logFileGenerator.generateLogFile(folder)
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        return logFile
    }

    class Builder(val logFolder: String) {
        var cleanStrategy: ICleanStrategy? = null
        var storageStrategy: IStorageStrategy? = null
        var logFileGenerator: ILogFileGenerator? = null
        var writer: IFileWriter? = null
        var typesetter: ITypesetter? = null
        var header: ILogHeader? = null
        var executor: ExecutorService? = null

        fun build(): FilePrintAdapter {
            return FilePrintAdapter(this)
        }
    }

    private inner class Worker : Runnable {
        private val logs = LinkedBlockingQueue<String>()
        var isStarted = false

        fun enqueue(log: String) {
            logs.put(log)
        }

        override fun run() {
            isStarted = true
            var log: String?
            try {
                while ((logs.take().also { log = it }) != null) {
                    doPrintln(log!!)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
                synchronized(this) {
                    isStarted = false
                }
            }
        }

    }
}