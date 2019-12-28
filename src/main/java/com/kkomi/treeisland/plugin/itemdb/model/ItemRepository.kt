package com.kkomi.treeisland.plugin.itemdb.model

interface ItemRepository<T> {

    fun getItem(code: String): T?

    fun getItemFromItemDisplay(display: String): T?

    fun getItemList(): List<T>

    fun createItem(code: String)

    fun removeItem(code: String)

    fun editItem(item: T)

    fun reloadItems()

}