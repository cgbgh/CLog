package me.cgb.clog.print

import me.cgb.clog.print.typeset.ITypesetter
import me.cgb.clog.print.typeset.ITypesetting

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class PrintAdapterSet : IPrintAdapter, Iterable<IPrintAdapter>, ITypesetting {
    private val printAdapters = mutableListOf<IPrintAdapter>()

    override fun println(level: Int, tag: String, msg: String) {
        for (adapter in printAdapters) {
            adapter.println(level, tag, msg)
        }
    }

    fun addPrintAdapter(adapter: IPrintAdapter) {
        if (!printAdapters.contains(adapter)) {
            printAdapters.add(adapter)
        }
    }

    fun removePrintAdapter(adapter: IPrintAdapter) {
        printAdapters.remove(adapter)
    }

    fun isEmpty() = printAdapters.isEmpty()
    override fun iterator(): Iterator<IPrintAdapter> {
        return printAdapters.iterator()
    }

    override var typesetter: ITypesetter? = null
        set(value) {
            for (adapter in printAdapters) {
                if (adapter is ITypesetting) {
                    adapter.typesetter = typesetter
                }
            }
            field = value
        }
}