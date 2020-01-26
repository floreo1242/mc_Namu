package com.kkomi.treeisland.plugin.skill.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateSkillInfo(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        SkillInfoRepository.addSkillInfo(SkillInfo(args.next(), "", "", 0, "공용", ""))
        return true
    }
}