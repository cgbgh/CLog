package me.cgb.clog

import me.cgb.clog.decoration.DefaultLogDecoration
import me.cgb.clog.decoration.ILogDecoration
import me.cgb.clog.filter.ILogFilter
import me.cgb.clog.format.IFormatAdapter
import me.cgb.clog.print.IPrintAdapter
import me.cgb.clog.strategy.DefaultPrintStrategy
import me.cgb.clog.strategy.IPrintStrategy

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> 日志打印配置
 * ================================================
 */
class Config {
    @CLog.Level
    var logLevel: Int = LogLevel.NOISY
    var globalTag: String = "CLog"
    var withThreadInfo: Boolean = false
    var withStackTrace: Boolean = false
    var stackTraceDepth: Int = 5
    var printer: IPrintAdapter? = null
    var printStrategy: IPrintStrategy = DefaultPrintStrategy()
    var formatAdapterMap: Map<Class<*>, IFormatAdapter<*>>? = null
    var logFilters: List<ILogFilter>? = null
    var logDecoration: ILogDecoration = DefaultLogDecoration()

    companion object {
        fun newBuilder(): Builder {
            return Builder()
        }
    }

    class Builder {

    }
}