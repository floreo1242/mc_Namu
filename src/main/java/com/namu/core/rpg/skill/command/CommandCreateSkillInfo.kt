package com.namu.core.rpg.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.skill.model.SkillInfoRepository
import com.namu.core.rpg.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateSkillInfo : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "스킬 정보를 생성합니다."

    override val usage: String = "<SkillInfoCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        SkillInfoRepository.createSkillInfo(SkillInfo(args.next(), "", "", 0, "공용", ""))
        sender.sendInfoMessage("스킬정보를 생성하였습니다.")
        return true
    }
}