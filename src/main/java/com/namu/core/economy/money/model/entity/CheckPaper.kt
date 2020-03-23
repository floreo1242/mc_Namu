package com.namu.core.economy.money.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.extension.toMoneyFormat
import com.kkomi.devlibrary.nms.addNBTTagCompound
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class CheckPaper (
        val publisher : String,
        val price : Int
) {
    fun toItemStack() : ItemStack {
        return createItemStack(
                Material.PAPER,
                "수표",
                listOf(
                        "&f금액 : &6${price.toMoneyFormat()}",
                        "&f출처 : &6$publisher"
                )
        ).run { addNBTTagCompound(this@CheckPaper) }
    }
}