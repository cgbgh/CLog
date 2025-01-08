package me.cgb.clog.filter

import me.cgb.clog.CLog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> Log filter, filter logs according to specified rules
 * ================================================
 */
interface ILogFilter {
    /**
     * Filter out the logs.
     *
     * @param tag       the tag of log
     * @param level  the level of log
     * @param msg       the msg of log
     * @return Whether filter out this log, if true filter out, otherwise print it.
     */
    fun filter(@CLog.Level level: Int, tag: String, msg: String): Boolean
}