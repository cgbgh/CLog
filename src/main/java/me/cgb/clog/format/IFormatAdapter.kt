package me.cgb.clog.format

/**
 * ================================================
 * @author cgb
 * @date 2025/1/4
 * @desc
 * <p> Format adapter, used to format different types of content into printable text
 * ================================================
 */
interface IFormatAdapter<D : Any> {
    fun format(data: D): String
}