package com.kkomi.treeisland.plugin.talkscript.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("TalkScript")
data class TalkScript(
        val name: String,
        val dialogList: MutableList<Dialog>,
        var runCommand: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): TalkScript {
            return TalkScript(
                    data["name"] as String,
                    data["dialogList"] as MutableList<Dialog>,
                    data["runCommand"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "dialogList" to dialogList,
                "runCommand" to runCommand
        )
    }

}
