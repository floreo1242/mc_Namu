package com.namu.core.rpg.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildOption")
class GuildOption(
        val upgradeValue: List<GuildUpgradeValue>,
        val defaultMaxMember: Int,
        val upgradeMemberCount: Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): GuildOption {
            return GuildOption(
                    data["upgradeMoney"] as List<GuildUpgradeValue>,
                    data["defaultMaxMember"] as Int,
                    data["upgradeMemberCount"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "upgradeMoney" to upgradeValue,
                "defaultMaxMember" to defaultMaxMember,
                "upgradeMemberCount" to upgradeMemberCount
        )
    }
}