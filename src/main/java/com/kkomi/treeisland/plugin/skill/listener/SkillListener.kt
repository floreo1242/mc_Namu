package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import com.kkomi.treeisland.plugin.skill.api.SpellApplyDamagePlayerByEntityEvent
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import com.nisovin.magicspells.events.MagicSpellsEntityDamageByEntityEvent
import org.bukkit.Bukkit
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import kotlin.random.Random
import kotlin.random.nextInt

class SkillListener : Listener {

    @EventHandler
    fun onSpellApplyDamagePlayerByEntityEvent(event: SpellApplyDamagePlayerByEntityEvent) {
        val entity = event.entity
        var damage = event.percent * event.caster.getPlayerInfo().statInfo.getTotalDamage()

        if (entity is Player) {
            val player = event.entity as Player
            val stat = player.getPlayerInfo().statInfo

            if (((stat.finalStat[StatOption.AGILITY]
                            ?: 0) * StatConfigRepository.getStatConfig().agiPointByValue) <= Random.nextInt(1..100)) {
                return
            } else {
                damage -= stat.finalStat[StatOption.DEFENSE] ?: 0
            }
        }

        event.entity.damage(if (damage < 0) 0.0 else damage)

        if (event.entity.isDead) {
            Bukkit.getPluginManager().callEvent(EntityDeathBySpellCasterEvent(event.caster, event.entity))
        }
    }

    @EventHandler
    fun onMagicSpellsEntityDamageByEntityEvent(event: MagicSpellsEntityDamageByEntityEvent) {
        Bukkit.getPluginManager().callEvent(
                SpellApplyDamagePlayerByEntityEvent(
                        false,
                        event.damager as? Player ?: return,
                        event.entity as? LivingEntity ?: return,
                        event.damage
                )
        )
    }

}