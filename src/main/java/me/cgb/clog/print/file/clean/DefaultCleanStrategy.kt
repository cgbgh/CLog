package me.cgb.clog.print.file.clean

import java.io.File

class DefaultCleanStrategy : ICleanStrategy {
    override fun shouldClean(file: File): Boolean {
        return false
    }

    override fun clean(folder: File) {
        for (file in folder.listFiles()) {
            file.delete()
        }
    }

}
