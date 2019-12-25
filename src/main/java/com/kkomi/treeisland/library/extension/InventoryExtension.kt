package com.kkomi.treeisland.library.extension

import com.kkomi.treeisland.plugin.quest.inventory.*
import com.kkomi.treeisland.plugin.shop.inventory.ShopInventory
import com.kkomi.treeisland.plugin.talkscript.inventory.TalkScriptInventory
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

fun Inventory.setItem(row: Int, col: Int, itemStack: ItemStack) {
    setItem(row * 9 + col, itemStack)
}

private val serverInventoryPrefixList = listOf(
        TalkScriptInventory.TITLE,
        ShopInventory.TITLE,
        QuestAcceptInventory.TITLE,
        QuestCancelInventory.TITLE,
        QuestListInventory.TITLE,
        QuestRewardInventory.TITLE,
        QuestStatusInventory.TITLE
)

fun Inventory.getServerTitleInfo(): Pair<String, String>? {
    val temp = title.split(" - ")

    if (temp.size != 2) {
        return null
    }

    if (!serverInventoryPrefixList.contains(temp[0])) {
        return null
    }

    return temp[0] to temp[1]
}