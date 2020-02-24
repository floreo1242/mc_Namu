package com.kkomi.treeisland.plugin.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadSkillInfo : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "스킬정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        SkillInfoRepository.reloadSkillInfo()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}