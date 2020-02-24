package com.kkomi.treeisland.plugin.itemdb.command.other

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateOtherItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "기타아이템을 생성합니다."

    override val usage: String = "<ItemCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        OtherItemRepository.createItem(args.next())
        return true
    }
}