package me.cgb.clog.print.file.clean

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/20
 * @desc
 * <p>
 * ================================================
 * 
 */
class LastModifiedCleanStrategy(private val maxExpire: Long): ICleanStrategy {
    override fun shouldClean(file: File): Boolean {
        return System.currentTimeMillis() - file.lastModified() > maxExpire
    }
}