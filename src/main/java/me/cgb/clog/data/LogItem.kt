package me.cgb.clog.data

import me.cgb.clog.CLog

/**
 * ================================================
 * @author cgb
 * @date 2025/1/12
 * @desc
 * <p>
 * ================================================
 */
data class LogItem(
    @CLog.Level val level: Int,
    val tag: String,
    val msg: String,
    val timeMillis: Long,
    val thread: Thread,
)
