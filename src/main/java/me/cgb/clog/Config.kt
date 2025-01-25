package me.cgb.clog

import me.cgb.clog.decoration.ILogDecoration
import me.cgb.clog.filter.ILogFilter
import me.cgb.clog.format.IFormatAdapter
import me.cgb.clog.format.json.IJsonFormatAdapter
import me.cgb.clog.format.stacktrace.IStackTraceFormatAdapter
import me.cgb.clog.format.thread.IThreadFormatAdapter
import me.cgb.clog.internal.Constants
import me.cgb.clog.internal.Defaults
import me.cgb.clog.print.IPrintAdapter
import me.cgb.clog.print.PrintAdapterSet
import me.cgb.clog.print.typeset.ITypesetter
import java.util.Collections

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> 日志打印配置
 * ================================================
 */
data class Config(
    @CLog.Level
    val logLevel: Int,
    val globalTag: String,
    val stackTraceDepth: Int,
    val printer: IPrintAdapter,
    val jsonFormatter: IJsonFormatAdapter,
    val formatAdapterMap: Map<Class<*>, IFormatAdapter<*>>? = null,
    val logFilters: List<ILogFilter>? = null,
    val logDecoration: ILogDecoration? = null,
    val threadFormatAdapter: IThreadFormatAdapter? = null,
    val stackTraceFormatAdapter: IStackTraceFormatAdapter? = null,
    var typesetter: ITypesetter? = null
) {

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()

        @JvmStatic
        fun newBuilder(): Builder {
            return Builder()
        }
    }

    class Builder {
        @CLog.Level
        var logLevel: Int = LogLevel.NOISY
        var globalTag: String = Constants.DEFAULT_GLOBAL_TAG
        var withThreadInfo: Boolean = false
        var withStackTrace: Boolean = false
        var stackTraceDepth: Int = 5
        var printer: PrintAdapterSet = PrintAdapterSet()
        var jsonFormatAdapter: IJsonFormatAdapter? = null
        var threadFormatAdapter: IThreadFormatAdapter? = null
        var stackTraceFormatAdapter: IStackTraceFormatAdapter? = null
        var formatAdapterMap: MutableMap<Class<*>, IFormatAdapter<*>>? = null
        var logFilters: MutableList<ILogFilter>? = null
        var logDecoration: ILogDecoration? = null
        var enableDecoration: Boolean = false
        var enableTypesetter: Boolean = false
        var typesetter: ITypesetter? = null

        fun logLevel(@CLog.Level logLevel: Int) = apply { this.logLevel = logLevel }
        fun globalTag(globalTag: String) = apply { this.globalTag = globalTag }
        fun withThreadInfo(withThreadInfo: Boolean) = apply { this.withThreadInfo = withThreadInfo }
        fun withStackTrace(stackTraceDepth: Int) = apply {
            this.withStackTrace = true
            this.stackTraceDepth = stackTraceDepth
        }

        fun addPrintAdapter(printer: IPrintAdapter) =
            apply { this.printer.addPrintAdapter(printer) }

        fun <D : Any> addFormatAdapter(clazz: Class<D>, adapter: IFormatAdapter<in D>) =
            apply {
                formatAdapterMap = formatAdapterMap
                    ?: mutableMapOf<Class<*>, IFormatAdapter<*>>().also { formatAdapterMap = it }
                formatAdapterMap!![clazz] = adapter
            }

        fun logFilter(filter: ILogFilter) = apply {
            logFilters = logFilters ?: mutableListOf<ILogFilter>().also { logFilters = it }
            logFilters!!.add(filter)
        }

        fun logDecoration(logDecoration: ILogDecoration) =
            apply { this.logDecoration = logDecoration }

        fun jsonFormatAdapter(jsonFormatter: IJsonFormatAdapter) =
            apply { this.jsonFormatAdapter = jsonFormatter }

        fun threadFormatAdapter(threadFormatAdapter: IThreadFormatAdapter) =
            apply { this.threadFormatAdapter = threadFormatAdapter }

        fun stackTraceFormatAdapter(stackTraceFormatAdapter: IStackTraceFormatAdapter) =
            apply { this.stackTraceFormatAdapter = stackTraceFormatAdapter }

        fun enableTypesetter(enable: Boolean) = apply {
            this.enableTypesetter = enable
        }

        fun typesetter(typesetter: ITypesetter) = apply {
            this.enableTypesetter = true
            this.typesetter = typesetter
        }

        fun build(): Config {
            initDefault()
            return Config(
                logLevel,
                globalTag,
                stackTraceDepth,
                printer,
                jsonFormatAdapter!!,
                formatAdapterMap,
                logFilters,
                logDecoration,
                threadFormatAdapter,
                stackTraceFormatAdapter,
                typesetter
            )
        }

        private fun initDefault() {
            if (printer.isEmpty()) {
                this.printer.addPrintAdapter(Defaults.defaultPrintAdapter())
            }
            jsonFormatAdapter = jsonFormatAdapter ?: Defaults.defaultJsonFormatAdapter()
            threadFormatAdapter = threadFormatAdapter
                ?: if (withThreadInfo) Defaults.defaultThreadFormatAdapter() else null
            stackTraceFormatAdapter =
                stackTraceFormatAdapter
                    ?: if (withStackTrace) Defaults.defaultStackTrackFormatAdapter() else null
            logDecoration = logDecoration
                ?: (if (enableDecoration) Defaults.defaultLogDecoration() else null)
            logFilters = logFilters ?: Collections.singletonList(Defaults.defaultLogFilter())

            if (enableTypesetter) {
                this.typesetter = this.typesetter ?: Defaults.defaultTypesetter()
            }
        }
    }
}