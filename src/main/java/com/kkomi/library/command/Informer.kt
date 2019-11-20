package com.kkomi.library.command

interface Informer<T> {
    fun information(index: Int, builder: StringBuilder, o: T)
}