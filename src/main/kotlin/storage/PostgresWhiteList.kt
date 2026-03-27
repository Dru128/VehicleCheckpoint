package org.dru128.storage

import org.dru128.access.WhiteList

// Имитация базы данных
object PostgresWhiteList: WhiteList {
    private val whiteList = mutableListOf(
        "A120CD",
        "C312HE",
        "M120CD",
        "N120CD",
        "E120CD",
        "P120CD",
    )

    override fun isAllowed(id: String): Boolean {
        return whiteList.contains(id)
    }

    override fun add(id: String) {
        whiteList.add(id)
    }

    override fun add(ids: Array<String>) {
        whiteList.addAll(ids)
    }

    override fun remove(id: String) {
        whiteList.remove(id)
    }
}
