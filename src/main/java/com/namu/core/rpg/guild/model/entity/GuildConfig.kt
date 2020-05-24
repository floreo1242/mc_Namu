package com.namu.core.rpg.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildConfig")
class GuildConfig(
        val upgradeValue: List<GuildUpgradeValue>,
        val defaultMaxMember: Int,
        val upgradeMemberCount: Int,
        val inviteTime : Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): GuildConfig {
            return GuildConfig(
                    data["upgradeMoney"] as List<GuildUpgradeValue>,
                    data["defaultMaxMember"] as Int,
                    data["upgradeMemberCount"] as Int,
                    data["inviteTime"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "upgradeMoney" to upgradeValue,
                "defaultMaxMember" to defaultMaxMember,
                "upgradeMemberCount" to upgradeMemberCount,
                "inviteTime" to inviteTime
        )
    }
}