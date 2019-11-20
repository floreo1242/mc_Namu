package com.kkomi.library.extension

import org.bukkit.entity.Player

fun Player.sendColorCodeMessage(message : String) {
    sendMessage(message.replace("&","§"))
}

fun Player.sendErrorMessage(message : String) {
    sendColorCodeMessage("&c[ERROR] &f$message")
}

fun Player.sendInfoMessage(message : String) {
    sendColorCodeMessage("&9[INFO] &f$message")
}
