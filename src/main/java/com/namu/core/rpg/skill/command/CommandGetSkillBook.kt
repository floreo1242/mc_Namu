package com.namu.core.rpg.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.rpg.skill.model.SkillInfoRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetSkillBook : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "스킬북을 발급합니다."

    override val usage: String = "<SkillInfoCode>"

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