package me.cgb.clog

import androidx.annotation.IntDef

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p>
 * ================================================
 */
object CLog {
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

    fun init() {
    }

    fun init(config: Config) {

    }
}