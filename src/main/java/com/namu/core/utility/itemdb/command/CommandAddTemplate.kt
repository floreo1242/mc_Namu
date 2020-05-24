package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.utility.itemdb.model.EquipmentType
import com.namu.core.utility.itemdb.model.CustomItemRepository
import com.namu.core.utility.itemdb.model.entity.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandAddTemplate : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipmentType.values()
                .map {
                    CustomItem(
                            code = "Template${it.name}",
                            description = listOf(),
                            equipmentOption = EquipmentOption(0, it, listOf(StatOption(StatType.HEALTH, 10))),
                            material = Material.STONE,
                            name = "Template${it.name}",
                            consumptionOption = null
                    )
                }
                .forEach {
                    CustomItemRepository.createCustomItem(it)
                    if (sender is Player) {
                        sender.inventory.addItem(it.toItemStack())
                    }
                }
        sender.sendInfoMessage("아이템을 생성하였습니다.")
        return true
    }

}