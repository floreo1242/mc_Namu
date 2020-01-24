package com.kkomi.treeisland.plugin.stat.model.entity

import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.nisovin.magicspells.MagicSpells
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import java.util.*
import kotlin.random.Random

@SerializableAs("PlayerStat")
data class PlayerStat(
        val uuid: String,
        val investmentStat: MutableMap<StatOption, Int>,
        var leftPoint: Int,
        var finalStat: MutableMap<StatOption, Int>
) : ConfigurationSerializable {

    private var minDamage = 0
    private var maxDamage = 0
    private var criticalChange = 0
    private var defense = 0

    fun updateFinalStat(playerEquipItem: PlayerEquipItem) {
        clearFinalStat()
        // equipment stat
        finalStat.putAll(playerEquipItem.getEquipmentItemStat())
        // investment
        investmentStat
                .forEach { (key, value) ->
                    finalStat[key] = (finalStat[key] ?: 0) + value
                }
    }

    fun calculateStatOption(player: Player) {

        // Walk Speed
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).baseValue =
                player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).defaultValue * (1 + (finalStat[StatOption.WALK_SPEED]
                        ?: 0) / 100)

        // Increment Health
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue = (20 + (finalStat[StatOption.HEALTH]
                ?: 0)).toDouble()

        // Increment Mana
        if (Bukkit.getPluginManager().isPluginEnabled(MagicSpells.plugin)) {
            MagicSpells.getManaHandler().setMaxMana(player, (finalStat[StatOption.MANA] ?: 0))
        }

        // Damage Calc
        this.minDamage = (finalStat[StatOption.MIN_DAMAGE] ?: 0)
        this.maxDamage = (finalStat[StatOption.MAX_DAMAGE] ?: 0)
        this.criticalChange = ((finalStat[StatOption.DEXTERITY] ?: 0) * 0.5).toInt()
        this.defense = ((finalStat[StatOption.DEFENSE] ?: 0) * 0.5).toInt()
    }

    private fun clearFinalStat() {
        StatOption.values().forEach { finalStat[it] = 0 }
    }

    fun getTotalDamage(): Double {
        val isCritical = criticalChange <= Random.nextInt() * 100
        return try {
            (Random.nextInt(minDamage, maxDamage) * (if (isCritical) 1 else 2)).toDouble()
        } catch (e: Exception) {
            (maxDamage * (if (isCritical) 1 else 2)).toDouble()
        }
    }

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerStat {
            return PlayerStat(
                    data["uuid"] as String,
                    (data["investmentStat"] as Map<String, Int>).mapKeys { StatOption.valueOf(it.key) } as MutableMap<StatOption, Int>,
                    data["leftPoint"] as Int,
                    (data["finalStat"] as Map<String, Int>).mapKeys { StatOption.valueOf(it.key) } as MutableMap<StatOption, Int>
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "uuid" to uuid,
                "investmentStat" to investmentStat.mapKeys { it.key.toString() },
                "leftPoint" to leftPoint,
                "finalStat" to finalStat.mapKeys { it.key.toString() }
        )
    }

}

