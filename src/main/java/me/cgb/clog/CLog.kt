package me.cgb.clog

import androidx.annotation.IntDef
import me.cgb.clog.format.IFormatAdapter
import me.cgb.clog.internal.Constants
import me.cgb.clog.utils.StackTraceUtils

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p>
 * ================================================
 */
object CLog {
    private var isInitialized = false
    internal lateinit var config: Config
    fun init() {
        init(Config.build {})
    }

    fun init(config: Config) {
        if (isInitialized) {
            return
        }
        isInitialized = true
        this.config = config
    }

    private fun assertInitialized() {
        if (!isInitialized) throw IllegalStateException("please init CLog first")
    }


    @JvmStatic
    fun println(@Level level: Int, obj: Any) {
        assertInitialized()
        if (config.logLevel > level) {
            return
        }
        printlnInternal(level, config.globalTag, obj2Msg(obj))
    }

    @JvmStatic
    fun println(@Level level: Int, msg: String, vararg args: Any) {
        assertInitialized()
        if (config.logLevel > level) {
            return
        }
        if (args.isNotEmpty()) {
            printlnInternal(level, config.globalTag, format(msg, *args))
        } else {
            printlnInternal(level, config.globalTag, msg)
        }
    }

    @JvmStatic
    fun printlnInternal(@Level level: Int, tag: String, msg: String) {
        if (!config.logFilters.isNullOrEmpty()) {
            for (filter in config.logFilters!!) {
                if (filter.filter(level, tag, msg)) return
            }
        }
        val thread = config.threadFormatAdapter?.format(Thread.currentThread())
        val stackTrace =
            config.stackTraceFormatAdapter?.format(StackTraceUtils.getCroppedStackTrace(config.stackTraceDepth))

        val log = config.logDecoration?.decorate(level, tag, arrayOf(thread, msg, stackTrace))
            ?: generateLog(thread, msg, stackTrace)
        config.printer.println(level, tag, log)
    }

    private fun generateLog(thread: String?, msg: String, stackTrace: String?): String {
        val builder = StringBuilder().apply {
            thread?.let { append(thread).append(Constants.LINE_SEPARATOR) }
            append(msg).append(Constants.LINE_SEPARATOR)
            stackTrace?.let { append(stackTrace).append(Constants.LINE_SEPARATOR) }
        }
        return builder.toString()
    }


    private fun <Obj : Any> obj2Msg(obj: Obj): String {
        var adapter: IFormatAdapter<in Obj>? = null
        var superClass: Class<in Obj>? = obj.javaClass
        while (adapter == null && superClass != null) {
            val clazz = superClass
            adapter = config.formatAdapterMap?.get(clazz) as? IFormatAdapter<in Obj>
            superClass = clazz.superclass
        }
        return adapter?.format(obj) ?: obj.toString()
    }

    private fun format(format: String, vararg args: Any): String {
        if (format.isEmpty()) {
            val builder = StringBuilder()
            for (arg in args) {
                builder.append(obj2Msg(arg)).append(", ")
            }
            builder.deleteAt(builder.length - 1)
            return builder.toString()
        }
        return String.format(format, *args)
    }

    @JvmStatic
    fun v(obj: Any) {
        println(LogLevel.VERBOSE, obj)
    }

    @JvmStatic
    fun v(msg: String, vararg args: Any) {
        println(LogLevel.VERBOSE, msg, *args)
    }

    @JvmStatic
    fun d(obj: Any) {
        println(LogLevel.DEBUG, obj)
    }

    @JvmStatic
    fun d(msg: String, vararg args: Any) {
        println(LogLevel.DEBUG, msg, *args)
    }

    @JvmStatic
    fun i(obj: Any) {
        println(LogLevel.INFO, obj)
    }

    @JvmStatic
    fun i(msg: String, vararg args: Any) {
        println(LogLevel.INFO, msg, *args)
    }

    @JvmStatic
    fun w(obj: Any) {
        println(LogLevel.WARN, obj)
    }

    @JvmStatic
    fun w(msg: String, vararg args: Any) {
        println(LogLevel.WARN, msg, *args)
    }

    @JvmStatic
    fun e(obj: Any) {
        println(LogLevel.ERROR, obj)
    }

    @JvmStatic
    fun e(msg: String, vararg args: Any) {
        println(LogLevel.ERROR, msg, *args)
    }

    @JvmStatic
    fun assert(obj: Any) {
        println(LogLevel.ASSERT, obj)
    }

    @JvmStatic
    fun assert(msg: String, vararg args: Any) {
        println(LogLevel.ASSERT, msg, *args)
    }

    @JvmStatic
    fun json(json: String) {
        assertInitialized()
        if (config.logLevel > LogLevel.DEBUG) {
            return
        }
        printlnInternal(LogLevel.DEBUG, config.globalTag, config.jsonFormatter.format(json))
    }

    @IntDef(
        LogLevel.VERBOSE,
        LogLevel.DEBUG,
        LogLevel.INFO,
        LogLevel.WARN,
        LogLevel.ERROR,
        LogLevel.ASSERT,
        LogLevel.SILENT,
        LogLevel.NOISY
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class Level
}