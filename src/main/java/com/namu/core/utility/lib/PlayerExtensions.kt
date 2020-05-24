package com.namu.core.utility.lib

import org.bukkit.entity.Player

fun Player.performCommand(
        command : String,
        withOp : Boolean = false
) {
    val isAlreadyOp = isOp

    if (withOp) {
        isOp = true
    }

    performCommand(command)

    if (withOp) {
        if (isAlreadyOp) {
            return
        }

        isOp = false
    }
}