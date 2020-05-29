package com.namu.core.rpg.calculate.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.mana.util.playerMana
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetStatus : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val target = args.next()
        val player = sender as Player

        when (target) {
            "HEALTH" -> {
                var value = args.nextInt()
                if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: 0.0) <= (player.health + value)) {
                    player.health = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: 0.0).toInt().toDouble()
                } else {
                    player.health += value
                }
            }
            "MANA" -> {
                player.playerMana.mana += args.nextInt()
            }
            else -> {
                sender.sendErrorMessage("HEALTH or MANA")
            }
        }

        return true
    }
}