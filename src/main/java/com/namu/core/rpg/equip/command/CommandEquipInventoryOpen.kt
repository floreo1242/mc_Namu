package com.namu.core.rpg.equip.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.equip.inventory.EquipInventory
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandEquipInventoryOpen : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipInventory(sender as Player).open()
        return true
    }

}