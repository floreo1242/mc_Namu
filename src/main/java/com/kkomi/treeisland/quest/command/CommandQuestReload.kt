package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.library.extension.sendDebugMessage
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.quest.QuestManager
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandQuestReload(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        QuestPlugin.questManager.clear()
        Treeisland.questPlugin.setupManagers()
        (sender as Player).sendDebugMessage("퀘스트 플러그인을 리로드 하였습니다.")
        return true
    }

}