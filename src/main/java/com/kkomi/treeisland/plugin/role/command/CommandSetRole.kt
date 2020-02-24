package com.kkomi.treeisland.plugin.role.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.RoleRepository
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandSetRole : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = ""

    override val usage: String = "<RoleCode> <PlayerName>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val targetName = args.next()
        val roleName = args.next()
        val role = RoleRepository.getRole(roleName)

        if (role == null) {
            sender.sendErrorMessage("존재하지 않는 직업 입니다.")
            return true
        }

        val targetRoleInfo = Bukkit.getOfflinePlayer(targetName).getPlayerInfo().roleInfo
        targetRoleInfo.roleName = role.name
        PlayerRoleRepository.editPlayerRole(targetRoleInfo)
        sender.sendDebugMessage("직업을 ${role.name}으로 변경하였습니다.")
        return true
    }

}