package me.cgb.clog.print

import me.cgb.clog.data.LogItem
import me.cgb.clog.print.typeset.ITypesetter
import me.cgb.clog.print.typeset.ITypesetting

/**
 * ================================================
 * @author cgb
 * @date 2025/1/13
 * @desc
 * <p>
 * ================================================
 */
abstract class AbstractTypesettingPrintAdapter : IPrintAdapter, ITypesetting {

    override var typesetter: ITypesetter? = null

    override fun println(level: Int, tag: String, msg: String) {
        val currentThread = Thread.currentThread()
        val logTime = System.currentTimeMillis()
        val item = LogItem(level, tag, msg, logTime, currentThread)
        println(LogItem(level, tag, typesetter?.typeset(item) ?: msg, logTime, currentThread))
    }

    abstract fun println(item: LogItem)
}