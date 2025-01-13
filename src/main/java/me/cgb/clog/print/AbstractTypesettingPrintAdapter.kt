package me.cgb.clog.print

import me.cgb.clog.data.LogItem
import me.cgb.clog.internal.Defaults
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

    override fun typeset(item: LogItem): String {
        val typesetter = typesetter ?: Defaults.defaultTypesetter()
        return typesetter.typeset(item)
    }

    override fun println(level: Int, tag: String, msg: String) {
        val currentThread = Thread.currentThread()
        val item = LogItem(
            level, tag, msg, System.currentTimeMillis(), currentThread.name, currentThread.id
        )
        println(typeset(item))
    }

    abstract fun println(content: String)
}