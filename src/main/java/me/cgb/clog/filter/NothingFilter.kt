package me.cgb.clog.filter

import me.cgb.clog.data.LogItem

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class NothingFilter : ILogFilter {
    override fun filter(logItem: LogItem): Boolean {
        return false
    }
}