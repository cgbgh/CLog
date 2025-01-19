package me.cgb.clog.print.view

import me.cgb.clog.data.LogItem
import me.cgb.clog.print.AbstractTypesettingPrintAdapter

/**
 * ================================================
 * @author cgb
 * @date 2025/1/13
 * @desc
 * <p>
 * ================================================
 */
class WindowPrintAdapter: AbstractTypesettingPrintAdapter() {

    override fun println(level: Int, tag: String, msg: String) {
        val currentThread = Thread.currentThread()
        val item = LogItem(
            level, tag, msg, System.currentTimeMillis(), currentThread.name, currentThread.id
        )
        println(typeset(item))
    }

    override fun println(content: String) {
//
    }
}