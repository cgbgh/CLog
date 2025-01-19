package me.cgb.clog.print.file.storage

import java.io.File

/**
 * ================================================
 * @author cgb
 * @date 2025/1/14
 * @desc
 * <p>
 * ================================================
 */
interface ILogStorageStrategy {

    fun shouldStored(writingFile: File): Boolean

}