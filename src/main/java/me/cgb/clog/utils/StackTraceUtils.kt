package me.cgb.clog.utils

/**
 * ================================================
 * @author cgb
 * @date 2025/1/6
 * @desc
 * <p>
 * ================================================
 */
object StackTraceUtils {
    fun getCroppedStackTrace(): Array<StackTraceElement> {
        return Throwable().stackTrace
    }

}