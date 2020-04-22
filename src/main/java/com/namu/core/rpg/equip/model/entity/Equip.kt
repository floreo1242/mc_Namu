package com.namu.core.rpg.equip.model.entity

import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable

data class Equip(
        var uuid: String,
        var weapon : Material,
        var helmet : Material,
        var chest : Material,
        var pants : Material,
        var boots : Material
) : ConfigurationSerializable {

    companion object{
        @JvmStatic
        fun deserialize(data: Map<String, Any>) : Equip {
            return Equip (
                    data["uuid"] as String,
                    data["weapon"] as Material,
                    data["helmet"] as Material,
                    data["chest"] as Material,
                    data["pants"] as Material,
                    data["boots"] as Material
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "uuid" to uuid,
                "weapon" to weapon,
                "helmet" to helmet,
                "chest" to chest,
                "pants" to pants,
                "boots" to boots
        )
    }

}