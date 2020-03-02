package com.kkomi.treeisland.plugin.integration.model

import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

fun Player.getPlayerInfo(): PlayerInfo {
    return PlayerInfo(this)
}

fun OfflinePlayer.getPlayerInfo(): OfflinePlayerInfo {
    return OfflinePlayerInfo(this)
}