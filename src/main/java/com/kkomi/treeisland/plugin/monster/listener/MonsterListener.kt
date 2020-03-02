package com.kkomi.treeisland.plugin.monster.listener

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.integration.model.PlayerDamageStatRepository
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.level.api.PlayerExpGetEvent
import com.kkomi.treeisland.plugin.monster.model.MonsterRepository
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import kotlin.random.Random
import kotlin.random.nextInt

class MonsterListener : Listener {

    @EventHandler
    fun onEntityDeathBySpellCasterEvent(event: EntityDeathBySpellCasterEvent) {
        val killer = event.caster
        val monster = MonsterRepository.getMonster(event.entity.name) ?: return

        val bonusXp = PlayerDamageStatRepository.getPlayerFinalStat(killer)[StatOption.BONUS_XP] ?: 0.0
        val bonusGold = PlayerDamageStatRepository.getPlayerFinalStat(killer)[StatOption.BONUS_GOLD] ?: 0.0

        val exp = (monster.dropExp * ((1 + bonusXp) / 100)).toInt()
        val money = (monster.dropMoney * ((1 + bonusGold) / 100)).toInt()

        killer.getPlayerInfo().apply {
            moneyInfo.money += money
            spawnHologram(killer, event.entity.location, exp, money)

            PlayerExpGetEvent(this)
                    .run {
                        Bukkit.getPluginManager().callEvent(this)
                        if (!isCancelled) {
                            levelInfo.exp += exp
                        }
                    }
        }.editPlayerInfo()

        monster.dropItem
                .filter {
                    it.chance > Random.nextInt(1..100)
                }
                .forEach {
                    killer.world.dropItem(killer.location, it.toItemStack())
                }
    }

    private fun spawnHologram(killer: Player, location: Location, exp: Int, gold: Int) {
        val hologram = HologramsAPI.createHologram(Treeisland.instance, location.add(0.0, 2.0, 0.0)).apply {
            appendTextLine("GOLD +$gold")
            appendTextLine("EXP +$exp")
            visibilityManager.isVisibleByDefault = false
            visibilityManager.isVisibleTo(killer)
        }
        Bukkit.getScheduler().runTaskLater(Treeisland.instance, { hologram.delete() }, 20L)
    }

}