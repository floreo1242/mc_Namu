package com.namu.core.rpg.quest.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.quest.inventory.QuestAcceptInventory
import com.namu.core.rpg.quest.inventory.QuestTalkInventory
import com.namu.core.rpg.quest.model.QuestMessage
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.model.entity.Quest
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenQuest : QuestCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "퀘스트를 시작합니다."

    override val usage: String = "<QuestCode>"

    override fun onCommand(commandSender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        QuestTalkInventory(commandSender as Player, quest).open()
        return true
    }

}