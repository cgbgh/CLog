package me.cgb.clog.print

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class PrintAdapterSet : IPrintAdapter, Iterable<IPrintAdapter> {
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
}