package com.namu.core.rpg.quest.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.quest.model.QuestRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadQuest : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "퀘스트 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        QuestRepository.reloadQuest()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }

}