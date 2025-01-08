package me.cgb.clog.filter

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class DefaultLogFilter: ILogFilter {
    override fun filter(level: Int, tag: String, msg: String): Boolean {
        return false
    }
}