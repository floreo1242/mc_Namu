package com.kkomi.treeisland.plugin.itemdb.model

interface ItemDataSource<T> {

    fun getItem(code: String): T?

    fun getItemList(): List<T>

    fun addItem(item: T)

    fun editItem(item: T)

    fun removeItem(code: String)

    fun reloadItems()

}