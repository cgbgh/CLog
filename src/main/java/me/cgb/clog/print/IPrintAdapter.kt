package me.cgb.clog.print

import me.cgb.clog.CLog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> Log printing adapter, adapted to different ways of log printing
 * ================================================
 */
interface IPrintAdapter {
    /**
     * Print log in new line.
     *
     * @param tag       the tag of log
     * @param level  the level of log
     * @param msg       the msg of log
     */
    fun println(tag: String, @CLog.Level level: Int, msg: String)
}