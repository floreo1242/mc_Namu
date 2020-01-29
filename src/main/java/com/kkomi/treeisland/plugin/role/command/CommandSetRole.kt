package com.kkomi.treeisland.plugin.role.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.RoleRepository
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandSetRole(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val targetName = args.next()
        val roleName = args.next()
        val role = RoleRepository.getRole(roleName)

        if (role == null) {
            sender.sendErrorMessage("존재하지 않는 오류 입니다.")
            return true
        }

        val targetRoleInfo = Bukkit.getOfflinePlayer(targetName).getPlayerInfo().roleInfo
        targetRoleInfo.roleName = role.name
        PlayerRoleRepository.editPlayerRole(targetRoleInfo)
        sender.sendDebugMessage("직업을 ${role.name}으로 변경하였습니다.")
        return true
    }

}