package com.kkomi.treeisland.plugin.monster.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.monster.model.MonsterRepository
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class MonsterListener : Listener {

    @EventHandler
    fun onEntityDeathEvent(event: EntityDeathEvent) {

        println("%s killed by %s".format(event.entity.name, event.entity.killer.name))

        val killer = event.entity.killer ?: return
        val monster = MonsterRepository.getMonster(event.entity.name) ?: return

        killer.getPlayerInfo().apply {
            levelInfo.exp += monster.dropExp
            moneyInfo.money += monster.dropMoney

            sendInfoMessage("EXP + %d".format(monster.dropExp))
            sendInfoMessage("GOLD + %d".format(monster.dropMoney))

            PlayerLevelRepository.checkLevelUp(this)
        }.editPlayerInfo()
    }

}