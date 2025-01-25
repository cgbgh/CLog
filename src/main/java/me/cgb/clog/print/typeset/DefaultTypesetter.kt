package me.cgb.clog.print.typeset

import me.cgb.clog.LogLevel
import me.cgb.clog.data.LogItem
import me.cgb.clog.internal.Constants
import java.text.SimpleDateFormat
import java.util.Locale

class DefaultTypesetter : ITypesetter {

    private val threadLocalDateFormat: ThreadLocal<SimpleDateFormat?> =
        object : ThreadLocal<SimpleDateFormat?>() {
            override fun initialValue(): SimpleDateFormat {
                return SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.US)
            }
        }

    override fun typeset(item: LogItem): String {
        val logs = item.msg.split(Constants.LINE_SEPARATOR)
        val dateFormat = threadLocalDateFormat.get()
        val time = dateFormat?.format(item.timeMillis) ?: ""
        val builder = StringBuilder()
        for (log in logs) {
            builder.append("$time ${item.thread.name}(${item.thread.id}) ")
                .append("${LogLevel.shortLevelName(item.level)}/${item.tag}: $log")
                .append(Constants.LINE_SEPARATOR)
        }
        if (builder.isNotEmpty()) {
            return builder.toString()
        }
        return "$time ${item.thread.name}(${item.thread.id}) ${LogLevel.shortLevelName(item.level)}/${item.tag}: ${item.msg}"
    }

}
