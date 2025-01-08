package me.cgb.clog.utils

import me.cgb.clog.CLog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/6
 * @desc
 * <p>
 * ================================================
 */
object StackTraceUtils {
    fun getCroppedStackTrace(stackTraceDepth: Int): Array<StackTraceElement?> {
        val stackTrace = Thread.currentThread().stackTrace
        var realDepth = 0
        for ((index, element) in stackTrace.reversed().withIndex()) {
            if (element.className.startsWith(CLog::class.java.packageName)) {
                realDepth = index
                break
            }
        }

        val ignoreDepth = stackTrace.size - realDepth
        val realStack = arrayOfNulls<StackTraceElement>(stackTraceDepth)
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, stackTraceDepth)
        return realStack
    }

}