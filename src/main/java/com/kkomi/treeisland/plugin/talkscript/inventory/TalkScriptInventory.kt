package com.kkomi.treeisland.plugin.talkscript.inventory

import com.kkomi.treeisland.library.extension.setItem
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class TalkScriptInventory(
        player: Player,
        val talkScript: TalkScript
) : InventoryManager(player) {

    companion object {
        const val TITLE = "스크립트 대화"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27,  "$TITLE - ${talkScript.name}")

    override fun setBasicFrame() {
        inventory.setItem(1, 4, talkScript.dialogList[0].toItemStack())
    }

}