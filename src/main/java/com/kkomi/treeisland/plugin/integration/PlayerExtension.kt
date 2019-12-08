package com.kkomi.treeisland.plugin.integration

import org.bukkit.entity.Player

fun Player.getPlayerInfo(): PlayerInfo {
    return PlayerInfo(this)
}