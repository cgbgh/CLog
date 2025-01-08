package me.cgb.clog.decoration

import me.cgb.clog.CLog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p> Log output border decoration
 * ================================================
 */
interface ILogDecoration {
    fun decorate(@CLog.Level level: Int, tag: String, segments: Array<String?>): String
}