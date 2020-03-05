package com.kkomi.treeisland.plugin.integration.listener

import com.kkomi.treeisland.plugin.integration.model.PlayerDamageStatRepository
import com.kkomi.treeisland.plugin.equipitem.api.PlayerWearEquipmentItemEvent
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import com.kkomi.treeisland.plugin.stat.api.PlayerStatUpdateEvent
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import com.nisovin.magicspells.events.MagicSpellsEntityDamageByEntityEvent
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import kotlin.random.Random
import kotlin.random.nextInt

class PlayerDamageListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        PlayerDamageStatRepository.setPlayerFinalStat(event.player)
    }

    @EventHandler
    fun onPlayerWearEquipmentItemEvent(event: PlayerWearEquipmentItemEvent) {
        PlayerDamageStatRepository.setPlayerFinalStat(event.player)

        // 패시브 스텟 처리
        passiveStat(event.player)
    }

    @EventHandler
    fun onPlayerStatUpdateEvent(event: PlayerStatUpdateEvent) {
        PlayerDamageStatRepository.setPlayerFinalStat(event.player)

        // 패시브 스텟 처리
        passiveStat(event.player)
    }

    private fun passiveStat(player: Player) {
        val playerStat = PlayerDamageStatRepository.getPlayerFinalStat(player)
        val healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)

        healthAttribute.baseValue = healthAttribute.defaultValue + (playerStat[StatOption.HEALTH] ?: 0.0)
        try {
            player.walkSpeed = (0.2 * (1 + ((playerStat[StatOption.WALK_SPEED]
                    ?: 0.0) / 100))).toFloat()
        } catch (exception: Exception) {
            player.walkSpeed = 1f
        }
    }

    @EventHandler
    fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {

        // When call -> player to entity or player

        if (event.damager is Player) {
            val damager = event.damager as Player
            val damagerStat = PlayerDamageStatRepository.getPlayerFinalStat(damager)

            val str = (damagerStat[StatOption.STRENGTH] ?: 0.0) * StatConfigRepository.getStatConfig().strPointByValue
            val dex = (damagerStat[StatOption.DEXTERITY] ?: 0.0) * StatConfigRepository.getStatConfig().dexPointByValue

            var lastedDamage: Double = 0.0

            // 최소 ~ 최대 공격력 처리
            val defaultDamage = try {
                Random.nextInt((damagerStat[StatOption.MIN_DAMAGE] ?: 0.0).toInt()..(damagerStat[StatOption.MAX_DAMAGE]
                        ?: 0.0).toInt()).toDouble()
            } catch (exception: Exception) {
                damagerStat[StatOption.MIN_DAMAGE] ?: 0.0
            }
            lastedDamage += defaultDamage

            // STR 스텟 처리
            lastedDamage += str

            // 크리티컬 여부 처리
            val isCritical = Random.nextInt(1..100) <= dex
            if (isCritical) {
                lastedDamage *= 2
            }

            event.damage = lastedDamage * (event.damage / 100)

        }

        if (event.entity is Player) {
            val entity = event.entity as Player
            val entityStat = PlayerDamageStatRepository.getPlayerFinalStat(entity)
            val agi: Double = (entityStat[StatOption.AGILITY]
                    ?: 0.0) * StatConfigRepository.getStatConfig().agiPointByValue

            // 방어력 % 만큼 데미지 감소
            val vit: Double = ((entityStat[StatOption.VITALITY]
                    ?: 0.0) * StatConfigRepository.getStatConfig().vitPointByValue) / 100.0

            // 회피를 하였다면 이벤트 캔슬
            if (Random.nextInt(1..100) <= agi) {
                event.isCancelled = true
                return
            }

            event.damage -= if (event.damage * vit < 0) 0.0 else event.damage * vit

        }

        val damager = (event.damager as? Player) ?: return
        val entity = (event.entity as? LivingEntity) ?: return

        damager.sendTitle("", "${(if(entity.health - event.damage < 0 ) 0.0 else entity.health - event.damage).toInt()}", 0, 20, 0)
    }

}