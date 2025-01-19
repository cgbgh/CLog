package me.cgb.clog.print.file.storage

import java.io.File

class DefaultLogStorageStrategy : ILogStorageStrategy{

    override fun shouldStored(writingFile: File): Boolean {
        return false
    }

}
