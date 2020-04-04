package com.namu.core.life.maker.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.namu.core.utility.itemdb.model.CustomItemRepository
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("Recipe")
data class Recipe(
        var categoryName: String,
        var materialList: List<RecipeMaterial>,
        var name: String,
        var resultItemCode: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Recipe {
            return Recipe(
                    data["categoryName"] as String,
                    data["materialList"] as List<RecipeMaterial>,
                    data["name"] as String,
                    data["resultItemCode"] as String
            )
        }
    }

    fun toItemStack(): ItemStack {
        return createItemStack(
                Material.BOOK,
                "&f$name&f 레시피",
                materialList.map {
                    val customItem = CustomItemRepository.getCustomItem(it.itemCode)
                    return@map if (customItem == null) {
                        "&f- &cnull"
                    } else {
                        "&f- ${customItem.name}&f * ${it.amount}"
                    }
                }
        ).run {
            addNBTTagCompound(this@Recipe)
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "categoryName" to categoryName,
                "materialList" to materialList,
                "name" to name,
                "resultItemCode" to resultItemCode
        )
    }
}