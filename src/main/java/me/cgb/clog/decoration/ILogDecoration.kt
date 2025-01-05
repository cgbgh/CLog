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
    fun decorate(tag: String, @CLog.Level level: Int, segments: Array<String>): String
}