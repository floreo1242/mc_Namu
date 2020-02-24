package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.quest.inventory.QuestStatusInventory
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenQuestStatus : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = "자신의 퀘스트 상태창을 열어봅니다."

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        QuestStatusInventory(sender as Player).open()
        return true
    }
}