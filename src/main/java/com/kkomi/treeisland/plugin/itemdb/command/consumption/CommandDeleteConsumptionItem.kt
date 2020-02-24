package com.kkomi.treeisland.plugin.itemdb.command.consumption

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandDeleteConsumptionItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "소비아이템을 삭제합니다."

    override val usage: String = "<ItemCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        ConsumptionItemRepository.removeItem(args.next())
        return true
    }
}