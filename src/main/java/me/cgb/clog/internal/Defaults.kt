package me.cgb.clog.internal

import me.cgb.clog.decoration.DefaultLogDecoration
import me.cgb.clog.filter.DefaultLogFilter
import me.cgb.clog.format.json.DefaultJsonFormatAdapter
import me.cgb.clog.format.stacktrace.DefaultStackTraceFormatAdapter
import me.cgb.clog.format.thread.DefaultThreadFormatAdapter
import me.cgb.clog.print.AndroidPrintAdapter
import me.cgb.clog.print.typeset.DefaultTypesetter

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

    fun defaultLogFilter() = DefaultLogFilter()

    fun defaultThreadFormatAdapter() = DefaultThreadFormatAdapter()

    fun defaultStackTrackFormatAdapter() = DefaultStackTraceFormatAdapter()

    fun defaultTypesetter() = DefaultTypesetter()
}