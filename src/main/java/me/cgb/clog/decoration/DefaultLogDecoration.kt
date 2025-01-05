package me.cgb.clog.decoration

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
        const val MAX_LENGTH = 128

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
        const val LINE_SEPARATOR = "\n"
    }

    override fun decorate(tag: String, level: Int, segments: Array<String>): String {
        if (segments.isEmpty()) {
            return ""
        }
        val builder = StringBuilder()
        builder.append(HORIZONTAL_DECORATION_TOP).append(LINE_SEPARATOR)
        for (i in segments.indices) {
            val segment = segments[i]
            if (segment.length > MAX_LENGTH) {
                var start = 0
                while (start < segment.length) {
                    val end = start + MAX_LENGTH
                    builder.append(VERTICAL_DECORATION)
                        .append(segment.substring(start, (end).coerceAtMost(segment.length)))
                        .append(VERTICAL_DECORATION)
                        .append(LINE_SEPARATOR)
                    start += MAX_LENGTH
                }
            } else {
                builder.append(VERTICAL_DECORATION).append(segment).append(VERTICAL_DECORATION)
            }
            if (i < segments.size - 1) {
                builder.append(HORIZONTAL_DECORATION_DIVIDER).append(LINE_SEPARATOR)
            }
        }
        builder.append(HORIZONTAL_DECORATION_BOTTOM).append(LINE_SEPARATOR)
        return builder.toString()
    }
}