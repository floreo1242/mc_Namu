package com.kkomi.treeisland.plugin.post.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.post.command.CommandSendItem.ItemType.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSendItem : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Send Item"

    override val usage: String = "<ItemType> <Code> / 손에든 아이템은 별도의 코드를 입력하지 않습니다."

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player
        when (valueOf(args.next())) {
            Hand -> player.getPlayerInfo().playerPostBox.postItems.add(player.inventory.itemInMainHand)
            Equipment -> {
                val code = args.next()
                val equipmentItem = EquipmentItemRepository.getItem(code)

                if (equipmentItem == null) {
                    sender.sendErrorMessage("존재하지 않는 아이템 입니다.")
                    return true
                }

                player.getPlayerInfo().playerPostBox.postItems.add(equipmentItem.toItemStack())
            }
            Consumption -> {
                val code = args.next()
                val consumption = ConsumptionItemRepository.getItem(code)

                if (consumption == null) {
                    sender.sendErrorMessage("존재하지 않는 아이템 입니다.")
                    return true
                }

                player.getPlayerInfo().playerPostBox.postItems.add(consumption.toItemStack())
            }
            Other -> {
                val code = args.next()
                val otherItem = OtherItemRepository.getItem(code)

                if (otherItem == null) {
                    sender.sendErrorMessage("존재하지 않는 아이템 입니다.")
                    return true
                }

                player.getPlayerInfo().playerPostBox.postItems.add(otherItem.toItemStack())
            }
        }
        return true
    }

    enum class ItemType {
        Hand,
        Equipment,
        Consumption,
        Other
    }

}