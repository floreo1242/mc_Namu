package com.kkomi.treeisland.plugin.skill.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.skill.inventory.LearnSkillListInventory
import com.kkomi.treeisland.plugin.skill.inventory.SkillOptionInventory
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSkillOption : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "스킬 옵션창을 엽니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        SkillOptionInventory(sender as Player).open()
        return true
    }
}