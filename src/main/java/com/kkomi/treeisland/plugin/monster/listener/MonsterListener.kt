package com.kkomi.treeisland.plugin.monster.listener

import com.kkomi.treeisland.library.extension.replaceChatColorCode
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.level.api.PlayerExpGetEvent
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.monster.model.MonsterRepository
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import kotlin.random.Random
import kotlin.random.nextInt

class MonsterListener : Listener {

    @EventHandler
    fun onEntityDeathBySpellCasterEvent(event: EntityDeathBySpellCasterEvent) {
        val killer = event.caster
        val monster = MonsterRepository.getMonster(event.entity.name) ?: return

        killer.getPlayerInfo().apply {
            levelInfo.exp += monster.dropExp * (1 + ((killer.getPlayerInfo().statInfo.finalStat[StatOption.BONUS_XP]
                    ?: 0) / 100))
            moneyInfo.money += monster.dropMoney * (1 + ((killer.getPlayerInfo().statInfo.finalStat[StatOption.BONUS_GOLD]
                    ?: 0) / 100))

            PlayerLevelRepository.checkLevelUp(this)
            Bukkit.getPluginManager().callEvent(PlayerExpGetEvent(false, this))
        }.editPlayerInfo()

        monster.dropItem
                .filter {
                    it.chance < Random.nextInt(1..100)
                }
                .forEach {
                    killer.world.dropItem(killer.location, it.toItemStack())
                }
    }

}