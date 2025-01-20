package me.cgb.clog.print.file.clean

import java.io.File

class NeverCleanStrategy : ICleanStrategy {
    override fun shouldClean(file: File): Boolean {
        return false
    }
}
