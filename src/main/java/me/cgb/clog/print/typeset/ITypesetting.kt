package me.cgb.clog.print.typeset

import me.cgb.clog.data.LogItem

/**
 * ================================================
 * @author cgb
 * @date 2025/1/13
 * @desc
 * <p> This is an interface involving typesetting functions, including setting a typesetter and initiating the typesetting process, among other related features.
 * ================================================
 */
interface ITypesetting {
    var typesetter: ITypesetter?

    fun typeset(item: LogItem): String
}