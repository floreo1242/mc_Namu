package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.utility.itemdb.model.CustomItemRepository
import com.namu.core.utility.itemdb.model.entity.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateCustomItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "create custom item"

    override val usage: String = "<code>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        CustomItemRepository.createCustomItem(
                CustomItem(
                        args.next(),
                        ConsumptionOption("Enter the command", 0, 0),
                        listOf("Enter the item's description"),
                        EquipmentOption(
                                1,
                                listOf(
                                        StatOption(
                                                StatType.STRENGTH,
                                                1
                                        )
                                )
                        ),
                        Material.WOODEN_SWORD,
                        "Enter the item name"
                )
        )
        sender.sendInfoMessage("아이템을 생성하였습니다.")
        return true
    }

}