package com.kkomi.treeisland.plugin.skill.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

class SkillOptionInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "스킬 설정"

        val SMART_KEY_USE = createItemStack(
                Material.WOOL,
                "&f스킬 스마트키 &a사용",
                listOf(
                        "",
                        "&7클릭 시 미사용으로 전환됩니다.",
                        "",
                        "&7스마트키를 사용시 슬롯이 변경되었을때 스킬을 사용합니다.",
                        "&7스마트키를 미 사용시 스킬북을 우클릭 했을때 스킬을 사용합니다."
                ),
                1,
                13
        )
        val SMART_KEY_UN_USE = createItemStack(
                Material.WOOL,
                "&f스킬 스마트키 &c미사용",
                listOf(
                        "",
                        "&7클릭 시 사용으로 전환됩니다.",
                        "",
                        "&7스마트키를 사용시 슬롯이 변경되었을때 스킬을 사용합니다.",
                        "&7스마트키를 미 사용시 스킬북을 우클릭 했을때 스킬을 사용합니다."
                ),
                1,
                14
        )
    }

    override val inventory: Inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        inventory.setItem(
                2,
                if (player.getPlayerInfo().skillInfo.isSmartSlot) {
                    SMART_KEY_USE
                } else {
                    SMART_KEY_UN_USE
                }
        )
    }

}