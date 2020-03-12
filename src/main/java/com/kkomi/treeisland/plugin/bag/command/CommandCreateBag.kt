package com.kkomi.treeisland.plugin.bag.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.bag.model.BagRepository
import com.kkomi.treeisland.plugin.bag.model.entity.Bag
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class CommandCreateBag : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "가방을 발급합니다."

    override val usage: String = "<BagLevel>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val line = args.nextInt()
        val bag = Bag(listOf(), line, UUID.randomUUID().toString())
        BagRepository.addBag(bag)
        (sender as Player).inventory.addItem(bag.toItemStack())
        return true
    }

}