package com.kkomi.treeisland.plugin.itemdb.command.consumption

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetConsumptionItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "소비아이템을 발급합니다."

    override val usage: String = "<ItemCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        (sender as Player).inventory.addItem(ConsumptionItemRepository.getItem(args.next())?.toItemStack())
        return true
    }
}