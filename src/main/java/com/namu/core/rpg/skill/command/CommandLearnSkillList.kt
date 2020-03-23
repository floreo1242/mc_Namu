package com.namu.core.rpg.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.rpg.skill.inventory.LearnSkillListInventory
import com.namu.core.rpg.skill.model.SkillInfoRepository
import com.namu.core.rpg.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandLearnSkillList : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "습득한 스킬목록을 확인합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        LearnSkillListInventory(sender as Player).open()
        return true
    }
}