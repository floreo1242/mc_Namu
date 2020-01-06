package com.kkomi.treeisland.plugin.craft.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerCraftInfo")
class PlayerCraftInfo(

) : ConfigurationSerializable {
    companion object {
        fun deserialize(data: Map<String, Any>): PlayerCraftInfo {
            return PlayerCraftInfo()
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf()
    }

}