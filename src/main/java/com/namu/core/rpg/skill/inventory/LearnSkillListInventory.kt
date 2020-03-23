package com.namu.core.rpg.skill.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.rpg.skill.model.SkillInfoRepository
import com.namu.core.rpg.skill.util.playerSkill
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class LearnSkillListInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "스킬 목록"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        player.playerSkill.learnSkills
                .mapNotNull {
                    SkillInfoRepository.getSkillInfo(it)
                }
                .forEach {
                    inventory.addItem(it.toItemStack())
                }
    }

}