package me.cgb.clog.format.stacktrace

import me.cgb.clog.internal.Constants

/**
 * ================================================
 * @author cgb
 * @date 2025/1/6
 * @desc
 * <p>
 * ================================================
 */
class DefaultStackTraceFormatAdapter : IStackTraceFormatAdapter {

    override fun format(data: Array<StackTraceElement?>): String {
        if (data.isEmpty()) {
            return ""
        }
        if (data.size == 1) return "ー " + data[0].toString()
        val builder = StringBuilder()
        for ((index, element) in data.withIndex()) {
            if (index == 0) {
                builder.append("┌ ").append(element.toString()).append(Constants.LINE_SEPARATOR)
            } else if (index != data.size - 1) {
                builder.append("├ ").append(element.toString()).append(Constants.LINE_SEPARATOR)
            } else {
                builder.append("└ ").append(element.toString())
            }
        }
        return builder.toString()
    }
}