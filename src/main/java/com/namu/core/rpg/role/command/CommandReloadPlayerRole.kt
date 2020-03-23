package com.namu.core.rpg.role.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.role.model.PlayerRoleRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadPlayerRole : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어 직업 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerRoleRepository.reloadPlayerRole()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }

}