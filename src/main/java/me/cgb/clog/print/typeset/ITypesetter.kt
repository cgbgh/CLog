package me.cgb.clog.print.typeset

import me.cgb.clog.data.LogItem

/**
 * ================================================
 * @author cgb
 * @date 2025/1/10
 * @desc
 * <p>
 * ================================================
 */
interface ITypesetter {

    fun typeset(item: LogItem): String

}