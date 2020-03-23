package com.namu.core.rpg.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.skill.model.PlayerSkillInfoRepository
import com.namu.core.rpg.skill.model.SkillInfoRepository
import com.namu.core.rpg.skill.model.entity.PlayerSkillInfo
import com.namu.core.rpg.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadPlayerSkillInfo : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어의 스킬 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerSkillInfoRepository.reloadPlayerSkillInfo()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}