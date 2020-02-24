package com.kkomi.treeisland.plugin.itemdb.command.consumption

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandReloadConsumptionItem : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "소비아이템을 리로드합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        ConsumptionItemRepository.reloadItems()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}