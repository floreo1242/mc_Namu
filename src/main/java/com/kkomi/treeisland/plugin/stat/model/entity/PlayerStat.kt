package com.kkomi.treeisland.plugin.stat.model.entity

import com.kkomi.treeisland.library.extension.getLore
import com.kkomi.treeisland.library.extension.removeChatColorCode
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.nisovin.magicspells.MagicSpells
import org.bukkit.attribute.Attribute
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import java.lang.IllegalArgumentException
import kotlin.random.Random

@SerializableAs("PlayerStat")
data class PlayerStat(
        val uuid: String,
        val pickingStat: MutableMap<StatOption, Int>,
        val leftPoint: Int,
        var finalStat: MutableMap<StatOption, Int>
) : ConfigurationSerializable {

    var minDamage = 0
    var maxDamage = 0
    var criticalDamage = 0
    var criticalChange = 0
    var defense = 0

    fun updateFinalStat(playerEquipItem: PlayerEquipItem) {
        val equipmentStat = mutableMapOf<StatOption, Int>()
        StatOption.values().forEach { equipmentStat[it] = 0 }

        playerEquipItem.toItemStackList().forEach { item ->
            val lore = item.getLore()!!
            for (i in 6..lore.size - 3) {
                val str = lore[i].removeChatColorCode()
                val data = str.split("+") // 0 = option, 1 = value
                val statOption = StatOption.values().find { it.strName == data[0].substring(0..data[0].length - 2) }!!
                val optionValue = if (data[1].endsWith("%")) {
                    data[1].substring(0..data[1].length - 2).toInt()
                } else {
                    data[1].toInt()
                }
                equipmentStat[statOption] = (equipmentStat[statOption] ?: 0) + optionValue
            }
        }
        finalStat.putAll(equipmentStat)
        pickingStat.forEach { (key, value) -> finalStat[key] = (finalStat[key] ?: 0) + value }
    }

    fun applyFinalStat(player: Player) {

        val strength = finalStat[StatOption.STRENGTH] ?: 0
        val stamina = finalStat[StatOption.STAMINA] ?: 0
        val nature = finalStat[StatOption.NATURE] ?: 0
        val mind = finalStat[StatOption.MIND] ?: 0

        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).baseValue = (player.walkSpeed * (1 + (finalStat[StatOption.WALK_SPEED]
                ?: 0) / 100)).toDouble()
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue = 20 + (finalStat[StatOption.MAX_HP]
                ?: 1) + (stamina * 1).toDouble()
        MagicSpells.getManaHandler().setMaxMana(player, (finalStat[StatOption.MAX_MP] ?: 0) + (mind * 1))

        this.minDamage = (finalStat[StatOption.MIN_DAMAGE] ?: 0) + (finalStat[StatOption.STATIC_DAMAGE]
                ?: 0) + (strength * 1)
        this.maxDamage = (finalStat[StatOption.MAX_DAMAGE] ?: 0) + (finalStat[StatOption.STATIC_DAMAGE]
                ?: 0) + (strength * 1)

        this.criticalChange = (finalStat[StatOption.CRITICAL_DAMAGE] ?: 0)
        this.criticalDamage = (finalStat[StatOption.CRITICAL_CHANCE] ?: 0)

        this.defense = (finalStat[StatOption.DEFENSE] ?: 0) + nature
    }

    fun getDamage(): Double {
        val isCritical = criticalChange <= Random.nextInt() * 100
        return try {
            Random.nextInt(minDamage, maxDamage) * (if (isCritical) 1 + criticalDamage / 100.0 else 1.0)
        } catch (exception: IllegalArgumentException) {
            maxDamage * (if (isCritical) 1 + criticalDamage / 100.0 else 1.0)
        }
    }

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerStat {
            return PlayerStat(
                    data["uuid"] as String,
                    (data["pickingStat"] as Map<String, Int>).mapKeys { StatOption.valueOf(it.key) } as MutableMap<StatOption, Int>,
                    data["leftPoint"] as Int,
                    (data["finalStat"] as Map<String, Int>).mapKeys { StatOption.valueOf(it.key) } as MutableMap<StatOption, Int>
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "uuid" to uuid,
                "pickingStat" to pickingStat.mapKeys { it.key.toString() },
                "leftPoint" to leftPoint,
                "finalStat" to finalStat.mapKeys { it.key.toString() }
        )
    }

}

