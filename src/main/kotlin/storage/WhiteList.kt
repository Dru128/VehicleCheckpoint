package org.dru128.access

interface WhiteList {
    fun isAllowed(id: String): Boolean
    fun add(id: String)
    fun add(ids: Array<String>)
    fun remove(id: String)
}