package com.namu.core.utility.post.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.utility.itemdb.model.CustomItemRepository
import com.namu.core.utility.post.command.CommandSendItem.ItemType.*
import com.namu.core.utility.post.util.playerPostBox
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSendItem : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Send Item"

    override val usage: String = "<ItemType> <Code> / 손에든 아이템은 별도의 코드를 입력하지 않습니다."

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player
        when (valueOf(args.next())) {
            Hand -> player.playerPostBox.postItems.add(player.inventory.itemInMainHand)
            CustomItem -> {
                val code = args.next()
                val otherItem = CustomItemRepository.getCustomItem(code)

                if (otherItem == null) {
                    sender.sendErrorMessage("존재하지 않는 아이템 입니다.")
                    return true
                }

                player.playerPostBox.postItems.add(otherItem.toItemStack())
            }
        }
        return true
    }

    enum class ItemType {
        Hand,
        CustomItem
    }

}