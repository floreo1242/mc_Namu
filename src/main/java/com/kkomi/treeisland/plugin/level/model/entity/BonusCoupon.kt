package com.kkomi.oz.level.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.kkomi.devlibrary.nms.getNBTTagCompound
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.lang.StringBuilder


data class BonusCoupon(
        val magnification: Int,
        val duration: Int
) {
    companion object {
        const val ITEM_TITLE = "&e경험치 쿠폰"
        fun getExpCouponToItemStack(itemStack: ItemStack): BonusCoupon? {
            return itemStack.getNBTTagCompound(BonusCoupon::class.java)
        }
    }

    fun toItemStack(): ItemStack {
        return createItemStack(
                type = Material.ENCHANTED_BOOK,
                display = ITEM_TITLE,
                lore = listOf(
                        "",
                        "&f획득배율 : $magnification%",
                        "&f지속시간 : ${toDateFormat(duration)}"
                )
        ).run {
            addNBTTagCompound(this@BonusCoupon)
        }
    }

    private fun toDateFormat(time: Int): String {
        val result = StringBuilder()

        val hour = time / 3600
        val min = time % 3600 / 60
        val sec = time % 3600 % 60

        if (hour == 0) {
            if (min != 0) {
                result.append("${min}분 ")
            }
        } else {
            result.append("${hour}시 ")
            result.append("${min}분 ")
        }

        result.append("${sec}초")

        return result.toString()
    }
}