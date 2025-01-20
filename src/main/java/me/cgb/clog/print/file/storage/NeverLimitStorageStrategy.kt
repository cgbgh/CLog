package me.cgb.clog.print.file.storage

import java.io.File

class NeverLimitStorageStrategy : IStorageStrategy {

    override fun isReachesLimit(file: File): Boolean {
        return false
    }
}
