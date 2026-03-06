import org.dru128.access.WhiteList
import java.io.File

class FileWhiteList(path: String) : WhiteList {
    private val file = File(path)

    init {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
    }

    private fun readAll(): MutableSet<String> {
        return file.readLines()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toMutableSet()
    }

    override fun isAllowed(id: String): Boolean {
        return readAll().contains(id)
    }

    override fun add(id: String) {
        val ids = readAll()
        if (ids.add(id)) {
            writeAll(ids)
        }
    }

    override fun add(ids: Array<String>) {
        val current = readAll()
        current.addAll(ids)
        writeAll(current)
    }

    override fun remove(id: String) {
        val ids = readAll()
        if (ids.remove(id)) {
            writeAll(ids)
        }
    }

    private fun writeAll(ids: Set<String>) {
        file.writeText(ids.joinToString("\n"))
    }
}