package com.kkomi.treeisland.integration

import org.bukkit.entity.Player

fun Player.getPlayerInfo(): PlayerInfo {
    return PlayerInfo(this)
}