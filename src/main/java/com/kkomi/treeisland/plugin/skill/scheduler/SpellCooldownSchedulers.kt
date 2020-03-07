package com.kkomi.treeisland.plugin.skill.scheduler

import com.kkomi.devlibrary.schedular.TimerManager
import com.kkomi.devlibrary.scoreboard.CustomScoreBoard
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.nisovin.magicspells.MagicSpells
import com.nisovin.magicspells.Spell
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class SpellCooldownSchedulers : TimerManager() {

    private val uuidBySpellList = mutableMapOf<String, List<Spell>>()

    override fun startEventTimer() {
        PlayerSkillInfoRepository.getPlayerSkillInfoList()
                .forEach { playerSkillInfo ->
                    registerUuidBySpellList(playerSkillInfo)
                }
    }

    private fun registerUuidBySpellList(playerSkillInfo: PlayerSkillInfo) {
        val spellList = playerSkillInfo.learnSkills
                .mapNotNull { spellName ->
                    SkillInfoRepository.getSkillInfo(spellName)
                }
                .mapNotNull { skillInfo ->
                    MagicSpells.getSpellByInGameName(skillInfo.magicSpellName)
                }
        uuidBySpellList[playerSkillInfo.uuid] = spellList
    }

    override fun runningEventTimer(count: Int) {
        Bukkit.getOnlinePlayers()
                .forEach { player ->
                    registerUuidBySpellList(player.getPlayerInfo().skillInfo)

                    val spellCooldownScoreboard = CustomScoreBoard()
                    spellCooldownScoreboard.create(
                            objectiveName = "스킬 쿨타임",
                            values = (uuidBySpellList[player.uniqueId.toString()] ?: listOf())
                                    .filter { spell ->
                                        spell.onCooldown(player)
                                    }
                                    .map { spell ->
                                        spell.name + " : " + spell.getCooldown(player).toInt()
                                    }
                    )
                    spellCooldownScoreboard.visibleScoreboard(player)
                }
    }

    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }

}