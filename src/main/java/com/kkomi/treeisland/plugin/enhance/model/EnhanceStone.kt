package com.kkomi.treeisland.plugin.enhance.model

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class EnhanceStone(
        val statOption: StatOption,
        val minValue: Int,
        val maxValue: Int,
        val chance: Int
) {
    fun toItemStack(): ItemStack {
        return createItemStack(
                type = Material.FLINT,
                display = "&6${statOption.strName} &f$chance% 강화석",
                lore = listOf(
                        "",
                        "&f증가 수치 : &6$minValue &f~ &6$maxValue",
                        "&f성공 확률 : &6$chance&f%"
                )
        ).run {
            addNBTTagCompound(this@EnhanceStone)
        }
    }
}