package me.cgb.clog.print

import android.util.Log

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class AndroidPrintAdapter : IPrintAdapter {
    override fun println(level: Int, tag: String, msg: String) {
        Log.println(level, tag, msg)
    }
}