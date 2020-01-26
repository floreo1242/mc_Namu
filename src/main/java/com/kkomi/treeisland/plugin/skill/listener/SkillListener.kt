package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import com.kkomi.treeisland.plugin.skill.api.SpellApplyDamagePlayerByEntityEvent
import com.nisovin.magicspells.events.MagicSpellsEntityDamageByEntityEvent
import com.nisovin.magicspells.events.SpellApplyDamageEvent
import org.bukkit.Bukkit
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class SkillListener : Listener {

    @EventHandler
    fun onSpellApplyDamagePlayerByEntityEvent(event: SpellApplyDamagePlayerByEntityEvent) {
        val damage = event.percent * event.caster.getPlayerInfo().statInfo.getTotalDamage()
        event.entity.damage(damage)

        if (event.entity.isDead) {
            Bukkit.getPluginManager().callEvent(EntityDeathBySpellCasterEvent(event.caster, event.entity))
        }
    }

    @EventHandler
    fun onSpellApplyDamageEvent(event: SpellApplyDamageEvent) {
        Bukkit.getPluginManager().callEvent(
                SpellApplyDamagePlayerByEntityEvent(
                        false,
                        event.caster,
                        event.target,
                        event.damage
                )
        )
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