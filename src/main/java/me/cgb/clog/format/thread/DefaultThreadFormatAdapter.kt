package me.cgb.clog.format.thread

/**
 * ================================================
 * @author cgb
 * @date 2025/1/6
 * @desc
 * <p>
 * ================================================
 */
class DefaultThreadFormatAdapter : IThreadFormatAdapter {
    override fun format(thread: Thread): String {
        thread.name
        thread.threadGroup?.name
        thread.id
        thread.priority
        return "Thread:" +
                "id=${thread.id} | name=${thread.name} " +
                "| group=${thread.threadGroup?.name} " +
                "| priority = ${thread.priority}"
    }
}