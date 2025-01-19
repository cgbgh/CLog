package me.cgb.clog.print.file.header

class DefaultLogHeader : ILogHeader {
    override fun createHeader(): String {
        return "================================================================================================================================"
    }

}
