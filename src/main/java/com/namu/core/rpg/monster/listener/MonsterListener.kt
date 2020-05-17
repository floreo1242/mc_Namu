package com.namu.core.rpg.monster.listener

import com.namu.core.economy.money.util.edit
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.rpg.level.api.PlayerExpGetEvent
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.rpg.monster.model.MonsterRepository
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import kotlin.random.Random
import kotlin.random.nextInt

class MonsterListener : Listener {

    @EventHandler
    fun onEntityDeathEvent(event: EntityDeathEvent) {

        val killer = event.entity.killer ?: return
        val monster = MonsterRepository.getMonster(event.entity.name) ?: return

//        val bonusXp = PlayerDamageStatRepository.getPlayerFinalStat(killer)[StatOption.BONUS_XP] ?: 0.0
//        val bonusGold = PlayerDamageStatRepository.getPlayerFinalStat(killer)[StatOption.BONUS_GOLD] ?: 0.0

        val bonusXp = 0.0
        val bonusGold = 0.0

        val exp = (monster.dropExp * (1 + bonusXp / 100)).toInt()
        val money = (monster.dropMoney * (1 + bonusGold / 100)).toInt()

        event.drops.clear()
        event.droppedExp = 0

        killer.playerMoney.apply { this.money += money }.edit()
        spawnHologram(killer, event.entity.location, exp, money)

        PlayerExpGetEvent(killer)
                .run {
                    Bukkit.getPluginManager().callEvent(this)
                    if (!isCancelled) {
                        player.playerLevel.apply { this.exp += exp }.edit()
                    }
                }

        monster.dropItem
                .filter {
                    it.chance > Random.nextInt(1..100)
                }
                .forEach {
                    killer.world.dropItem(killer.location, it.toItemStack())
                }
    }

    private fun spawnHologram(killer: Player, location: Location, exp: Int, gold: Int) {
//        val hologram = HologramsAPI.createHologram(Treeisland.instance, location.add(0.0, 2.0, 0.0)).apply {
//            appendTextLine("GOLD +$gold")
//            appendTextLine("EXP +$exp")
//            visibilityManager.isVisibleByDefault = false
//            visibilityManager.isVisibleTo(killer)
//        }
//        Bukkit.getScheduler().runTaskLater(Treeisland.instance, { hologram.delete() }, 20L)
    }

}