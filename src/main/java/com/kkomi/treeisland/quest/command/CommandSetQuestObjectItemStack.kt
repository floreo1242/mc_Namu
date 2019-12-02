package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestObjectItemStack(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val itemStack = player.inventory.itemInMainHand
        if (itemStack == null) {
            player.sendErrorMessage("손에 아이템을 들고 명령어를 입력해주세요.")
            return true
        }
        quest.itemStackObject = itemStack
        quest.save()
        player.sendInfoMessage("목표 아이템을 설정하였습니다.")
        return true
    }

}