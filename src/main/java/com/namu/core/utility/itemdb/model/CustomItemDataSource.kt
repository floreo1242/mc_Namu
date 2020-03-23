package com.namu.core.utility.itemdb.model

import com.namu.core.utility.itemdb.model.entity.CustomItem

interface CustomItemDataSource {

    fun getCustomItem(name: String): CustomItem?

    fun getCustomItemList(): List<CustomItem>

    fun createCustomItem(customItem: CustomItem)

    fun editCustomItem(customItem: CustomItem)

    fun removeCustomItem(name: String)

    fun saveCustomItem(customItem: CustomItem)

    fun reloadCustomItem()

}