package me.cgb.clog.print.file.clean

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/13
 * @desc
 * <p>
 * ================================================
 */
interface ICleanStrategy {

    fun shouldClean(file: File): Boolean

    fun clean(folder: File)

}