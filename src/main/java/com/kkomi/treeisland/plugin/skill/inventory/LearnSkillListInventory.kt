package com.kkomi.treeisland.plugin.skill.inventory

import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class LearnSkillListInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "스킬 목록"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        player.getPlayerInfo().skillInfo.learnSkills
                .mapNotNull {
                    SkillInfoRepository.getSkillInfo(it)
                }
                .forEach {
                    inventory.addItem(it.toItemStack())
                }
    }

}