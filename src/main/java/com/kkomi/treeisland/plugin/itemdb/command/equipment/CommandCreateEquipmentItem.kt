package com.kkomi.treeisland.plugin.itemdb.command.equipment

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateEquipmentItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "장비아이템을 생성합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipmentItemRepository.createItem(args.next())
        return true
    }
}