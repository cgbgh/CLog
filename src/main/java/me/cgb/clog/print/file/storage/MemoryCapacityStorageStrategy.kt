package me.cgb.clog.print.file.storage

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/20
 * @desc
 * <p>
 * ================================================
 */
class MemoryCapacityStorageStrategy(private val maxCapacity: Long): IStorageStrategy {
    override fun isReachesLimit(file: File): Boolean {
        return file.length() >= maxCapacity
    }

}