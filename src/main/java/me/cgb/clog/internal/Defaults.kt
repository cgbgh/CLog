package me.cgb.clog.internal

import me.cgb.clog.decoration.DefaultLogDecoration
import me.cgb.clog.filter.NothingFilter
import me.cgb.clog.format.json.DefaultJsonFormatAdapter
import me.cgb.clog.format.stacktrace.DefaultStackTraceFormatAdapter
import me.cgb.clog.format.thread.DefaultThreadFormatAdapter
import me.cgb.clog.print.AndroidPrintAdapter
import me.cgb.clog.print.file.clean.NeverCleanStrategy
import me.cgb.clog.print.file.generator.GlobalTagFileGenerator
import me.cgb.clog.print.file.header.DefaultLogHeader
import me.cgb.clog.print.file.storage.NeverLimitStorageStrategy
import me.cgb.clog.print.file.writer.DefaultFileWriter
import me.cgb.clog.print.typeset.DefaultTypesetter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
object Defaults {
    fun defaultPrintAdapter() = AndroidPrintAdapter()

    fun defaultLogDecoration() = DefaultLogDecoration()

    fun defaultJsonFormatAdapter() = DefaultJsonFormatAdapter()

    fun defaultLogFilter() = NothingFilter()

    fun defaultThreadFormatAdapter() = DefaultThreadFormatAdapter()

    fun defaultStackTrackFormatAdapter() = DefaultStackTraceFormatAdapter()

    fun defaultTypesetter() = DefaultTypesetter()

    fun defaultFileWriter() = DefaultFileWriter()

    fun defaultStorageStrategy() = NeverLimitStorageStrategy()

    fun defaultLogFileGenerator() = GlobalTagFileGenerator()

    fun defaultCleanStrategy() = NeverCleanStrategy()

    fun defaultFileHeader() = DefaultLogHeader()

    fun defaultFilePrintExecutor(): ExecutorService = Executors.newSingleThreadExecutor()

}