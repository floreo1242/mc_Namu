package com.namu.core.rpg.skill.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.rpg.role.util.playerRole
import com.namu.core.rpg.skill.model.PlayerSkillInfoRepository
import com.namu.core.rpg.skill.model.entity.PlayerSkillInfo
import com.namu.core.rpg.skill.model.entity.SkillBookItemMeta
import com.namu.core.rpg.skill.util.playerSkill
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerSkillInfoListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val playerSkillInfo = PlayerSkillInfoRepository.getPlayerSkillInfo(event.player.uniqueId.toString())

        if (playerSkillInfo == null) {
            PlayerSkillInfoRepository.createPlayerSkillInfo(PlayerSkillInfo(event.player.uniqueId.toString(), false, mutableListOf()))
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {

        if (event.hand != EquipmentSlot.HAND) {
            return
        }

        val player = event.player
        val playerSkillInfo = player.playerSkill
        val itemStack = event.player.inventory.itemInMainHand ?: return
        val skillBookItemMeta = itemStack.getNBTTagCompound(SkillBookItemMeta::class.java) ?: return
        val skillInfo = skillBookItemMeta.skillInfo

        if (skillBookItemMeta.isSkillBook) {

            if (player.playerLevel.level < skillInfo.levelLimit) {
                player.sendErrorMessage("습득 할 수 없는 스킬입니다.")
                return
            }

            if (skillInfo.roleLimit != "공용" && player.playerRole.roleName != skillInfo.roleLimit) {
                player.sendErrorMessage("습득 할 수 없는 스킬입니다.")
                return
            }

            if (playerSkillInfo.learnSkills.contains(skillInfo.name)) {
                player.sendErrorMessage("이미 습득한 스킬입니다.")
                return
            }

            playerSkillInfo.learnSkills.add(skillInfo.name)
            player.inventory.setItemInMainHand(null)
            player.sendInfoMessage("${skillInfo.displayName} 스킬을 흭득 하셨습니다.")

        } else {

            if (!playerSkillInfo.learnSkills.contains(skillInfo.name)) {
                player.sendErrorMessage("습득하지 않은 스킬입니다.")
                return
            }
            skillInfo.cast(player)
        }
    }

}