package com.namu.core.rpg.calculate.model

import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.rpg.mana.model.ManaConfigRepository
import com.namu.core.rpg.stat.model.StatConfigRepository
import com.namu.core.rpg.stat.util.playerStat
import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import java.util.*

data class PlayerStatus(
        val uuid: String,
        var maxHealth: Int,
        var maxMana: Int,
        var movementSpeed: Int,
        var damage: Int
) {
    val player = Bukkit.getPlayer(UUID.fromString(uuid))!!

    fun calculate() {
        // max health
        var healthValue = 0
        healthValue += player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.defaultValue.toInt()
        healthValue += calculateStatValue(StatType.HEALTH)
        maxHealth = healthValue

        // max mana
        var manaValue = 0
        manaValue += calculateStatValue(StatType.MANA)
        manaValue += ManaConfigRepository.getManaConfig().levelByMana[player.playerLevel.level] ?: error("")
        maxMana = manaValue

        // walk speed
        var movementValue = 0
        movementValue += calculateStatValue(StatType.WALK_SPEED)
        movementSpeed = movementValue

        // damage
        var damageValue = 0
        damageValue += calculateStatValue(StatType.STRENGTH)
        damage = damageValue
    }

    fun apply() {
        player.healthScale = 20.0
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = maxHealth.toDouble()
        player.walkSpeed = (200 * (1 + movementSpeed / 100) / 1000.0).toFloat()
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue = damage.toDouble()
    }

    private fun calculateStatValue(statType: StatType): Int {
        return (player.playerStat.investmentStat[statType]!! * (StatConfigRepository.getStatConfig().pointByValue[statType]
                ?: error(""))).toInt()
    }
}