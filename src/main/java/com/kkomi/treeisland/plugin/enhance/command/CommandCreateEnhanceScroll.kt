package com.kkomi.treeisland.plugin.enhance.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.enhance.model.EnhanceStone
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandCreateEnhanceScroll : CommandComponent() {

    override val argumentsLength: Int = 4

    override val description: String = "Create enhance scroll"

    override val usage: String = "<StatOption> <MinValue> <MaxValue> <Chance>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val enhanceScroll = EnhanceStone(
                statOption = StatOption.valueOf(args.next()),
                minValue = args.nextInt(),
                maxValue = args.nextInt(),
                chance = args.nextInt()
        )
        (sender as Player).inventory.addItem(enhanceScroll.toItemStack())
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, componentLabel: String, args: ArgumentList): Iterable<String> {
        return if (args.getCursor() == 1) {
            StatOption.values().map { it.name }
        } else {
            listOf()
        }
    }

}