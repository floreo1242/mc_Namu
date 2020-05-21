package com.namu.core.rpg.equip.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandPlayerEquipInfoReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerEquipInfoRepository.reloadPlayerEquipInfo()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}