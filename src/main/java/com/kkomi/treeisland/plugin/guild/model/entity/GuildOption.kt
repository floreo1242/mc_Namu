package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildOption")
class GuildOption (
        val upgradeMoney : List<Int>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data : Map<String,Any>) : GuildOption {
            return GuildOption(data["upgradeMoney"] as List<Int>)
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "upgradeMoney" to upgradeMoney
        )
    }
}