package com.kkomi.treeisland.library.extension

fun MutableMap<String, Int>.count(key: String, value: Int = 1) {
    this[key] = (this[key] ?: 0) + value
}