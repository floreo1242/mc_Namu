package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.devlibrary.extension.getDisplay
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.kkomi.treeisland.plugin.skill.model.entity.SkillBookItemMeta
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerSkillInfoListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val playerSkillInfo = PlayerSkillInfoRepository.getPlayerSkillInfo(event.player.uniqueId.toString())

        if (playerSkillInfo == null) {
            PlayerSkillInfoRepository.addPlayerSkillInfo(PlayerSkillInfo(event.player.uniqueId.toString(), false, mutableListOf()))
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {

        if (event.hand != EquipmentSlot.HAND) {
            return
        }

//        if (!event.player.isOp) {
//            event.isCancelled = true
//        }

        val player = event.player
        val playerSkillInfo = player.getPlayerInfo().skillInfo
        val itemStack = event.player.inventory.itemInMainHand ?: return
        val skillBookItemMeta = itemStack.getNBTTagCompound(SkillBookItemMeta::class.java) ?: return
        val skillInfo = skillBookItemMeta.skillInfo

        if (skillBookItemMeta.isSkillBook) {

            if (player.getPlayerInfo().levelInfo.level < skillInfo.levelLimit) {
                player.sendErrorMessage("습득 할 수 없는 스킬입니다.")
                return
            }

            if (skillInfo.roleLimit != "공용" && player.getPlayerInfo().roleInfo.roleName != skillInfo.roleLimit) {
                player.sendErrorMessage("습득 할 수 없는 스킬입니다.")
                return
            }

            if (playerSkillInfo.learnSkills.contains(skillInfo.name)) {
                player.sendErrorMessage("이미 습득한 스킬입니다.")
                return
            }

            playerSkillInfo.learnSkills.add(skillInfo.name)
            player.inventory.itemInMainHand = null
            player.sendInfoMessage("${skillInfo.displayName} 스킬을 흭득 하셨습니다.")
            player.getPlayerInfo().editPlayerInfo()

        } else {

            if (!playerSkillInfo.learnSkills.contains(skillInfo.name)) {
                player.sendErrorMessage("습득하지 않은 스킬입니다.")
                return
            }
            skillInfo.cast(player)
        }
    }

    @EventHandler
    fun onPlayerChangeCursor(event: PlayerItemHeldEvent) {
        val player = event.player
        val playerSkillInfo = player.getPlayerInfo().skillInfo

        // Check is smart slot?
        if (!playerSkillInfo.isSmartSlot) {
            return
        }

        val itemStack = event.player.inventory.getItem(event.newSlot) ?: return
        val skillBookItemMeta = itemStack.getNBTTagCompound(SkillBookItemMeta::class.java) ?: return

        if (!playerSkillInfo.learnSkills.contains(skillBookItemMeta.skillInfo.name)) {
            player.sendErrorMessage("습득하지 않은 스킬입니다.")
            return
        }

        skillBookItemMeta.skillInfo.cast(player)
        player.inventory.heldItemSlot = 0
    }
}