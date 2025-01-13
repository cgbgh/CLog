package me.cgb.clog.print

/**
 * ================================================
 * @author cgb
 * @date 2025/1/10
 * @desc
 * <p>
 * ================================================
 */
class ConsolePrintAdapter : AbstractTypesettingPrintAdapter() {
    override fun println(content: String) {
        kotlin.io.println(content)
    }
}