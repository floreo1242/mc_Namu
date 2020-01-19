package com.kkomi.treeisland.plugin.monster.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.monster.model.MonsterRepository
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class MonsterListener : Listener {

    @EventHandler
    fun onEntityDeathBySpellCasterEvent(event: EntityDeathBySpellCasterEvent) {
        val killer = event.caster
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