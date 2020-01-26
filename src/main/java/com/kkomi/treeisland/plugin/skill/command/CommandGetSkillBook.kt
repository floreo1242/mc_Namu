package com.kkomi.treeisland.plugin.skill.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetSkillBook(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val skillName = args.next()
        val skillInfo = SkillInfoRepository.getSkillInfo(skillName)
        if (skillInfo == null) {
            sender.sendErrorMessage("존재하지 않는 스킬입니다.")
            return true
        }
        (sender as Player).inventory.addItem(skillInfo.toItemStack(true))
        return true
    }
}