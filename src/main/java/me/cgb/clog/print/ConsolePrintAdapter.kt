package me.cgb.clog.print

import me.cgb.clog.data.LogItem

/**
 * ================================================
 * @author cgb
 * @date 2025/1/10
 * @desc
 * <p>
 * ================================================
 */
class ConsolePrintAdapter : AbstractTypesettingPrintAdapter() {
    override fun println(item: LogItem) {
        println(item.msg)
    }
}