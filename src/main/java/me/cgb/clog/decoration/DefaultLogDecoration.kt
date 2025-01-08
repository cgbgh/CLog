package me.cgb.clog.decoration

import me.cgb.clog.format.stacktrace.Constants

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class DefaultLogDecoration : ILogDecoration {
    companion object {
        const val MAX_LENGTH = 128 - 4

        //        ┏ + 128 ━ + ┓
        const val HORIZONTAL_DECORATION_TOP =
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"

        const val VERTICAL_DECORATION = "┃"

        //        ┣ + 128 - + ┫
        const val HORIZONTAL_DECORATION_DIVIDER =
            "┣----------------------------------------------------------------" +
                    "----------------------------------------------------------------┫"

        //        ┗ + 128 ━ + ┛
        const val HORIZONTAL_DECORATION_BOTTOM =
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"

        const val INDENT = "  "
        const val SPACE = " "
    }

    override fun decorate(level: Int, tag: String, segments: Array<String?>): String {
        if (segments.isEmpty()) {
            return ""
        }
        val builder = StringBuilder()
        builder.append(HORIZONTAL_DECORATION_TOP).append(Constants.LINE_SEPARATOR)
        for (i in segments.indices) {
            val segment = segments[i] ?: continue
            val logs = segment.split(Constants.LINE_SEPARATOR)
            for (log in logs) {
                decorateLine(log, builder)
            }
            if (i < segments.size - 1) {
                builder.append(HORIZONTAL_DECORATION_DIVIDER).append(Constants.LINE_SEPARATOR)
            }
        }
        builder.append(HORIZONTAL_DECORATION_BOTTOM).append(Constants.LINE_SEPARATOR)
        return builder.toString()
    }

    private fun decorateLine(log: String, builder: StringBuilder) {
        if (log.length > MAX_LENGTH) {
            var start = 0
            while (start < log.length) {
                val end = start + MAX_LENGTH
                builder.append(VERTICAL_DECORATION).append(INDENT)
                    .append(log.substring(start, (end).coerceAtMost(log.length)))
                if (end > log.length) {
                    for (count in 0 until (end - log.length)) {
                        builder.append(SPACE)
                    }
                }
                builder.append(INDENT).append(VERTICAL_DECORATION)
                    .append(Constants.LINE_SEPARATOR)
                start += MAX_LENGTH
            }
        } else {
            builder.append(VERTICAL_DECORATION).append(INDENT).append(log)
            if (MAX_LENGTH - log.length > 0) {
                for (count in 0 until (MAX_LENGTH - log.length + 2)) {
                    builder.append(SPACE)
                }
            }
            builder.append(VERTICAL_DECORATION).append(Constants.LINE_SEPARATOR)
        }
    }
}