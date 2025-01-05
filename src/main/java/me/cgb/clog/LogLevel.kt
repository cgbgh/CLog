package me.cgb.clog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> 日志级别
 * ================================================
 */
object LogLevel {
    const val VERBOSE: Int = 2
    const val DEBUG: Int = 3
    const val INFO: Int = 4
    const val WARN: Int = 5
    const val ERROR: Int = 6
    const val ASSERT: Int = 7
    const val SILENT: Int = Int.MAX_VALUE
    const val NOISY: Int = Int.MIN_VALUE

    @JvmStatic
    fun levelName(@CLog.Level level: Int): String {
        return when (level) {
            VERBOSE -> "VERBOSE"
            DEBUG -> "DEBUG"
            INFO -> "INFO"
            WARN -> "WARN"
            ERROR -> "ERROR"
            ASSERT -> "ASSERT"
            SILENT -> "SILENT"
            NOISY -> "NOISY"
            else -> "LogLevel-$level"
        }
    }

    @JvmStatic
    fun shortLevelName(@CLog.Level level: Int): String {
        return when (level) {
            VERBOSE -> "V"
            DEBUG -> "D"
            INFO -> "I"
            WARN -> "W"
            ERROR -> "E"
            ASSERT -> "A"
            SILENT -> "S"
            NOISY -> "N"
            else -> "L-$level"
        }
    }
}