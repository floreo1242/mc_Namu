package com.kkomi.treeisland.integration

import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid : String = player.uniqueId.toString()
}