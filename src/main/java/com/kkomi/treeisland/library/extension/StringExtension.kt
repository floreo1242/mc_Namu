package com.kkomi.treeisland.library.extension

import org.bukkit.ChatColor

fun String.replaceChatColorCode() : String {
    return replace("&","§")
}

fun String.removeChatColorCode() : String {
    return ChatColor.stripColor(this)
}
