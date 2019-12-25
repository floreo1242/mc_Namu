package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.library.extension.*
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@SerializableAs("Quest")
data class Quest(
        val name: String,
        var title: String = "",
        var limitLv: Int = 0,
        var type: QuestType = QuestType.NORMAL,
        var beforeQuest: String = "",
        var startNpc: String = "",
        var endNpc: String = "",
        var talks: List<String> = listOf(),
        var acceptMessage: String = "",
        var disposeMessage: String = "",
        var purposeMessage: String = "",
        var completeMessage: String = "",
        var action: QuestAction = QuestAction.BLOCK_BREAK,
        var stringObject: String = "",
        var locationObject: Location = emptyLocation(),
        var itemStackObject: ItemStack = ItemStack(Material.STONE),
        var count: Int = 1,
        var rewardItems: List<ItemStack> = listOf(),
        var rewardCommand: String = ""
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Quest {
            return Quest(
                    data["name"] as String,
                    data["title"] as String,
                    data["limitLv"] as Int,
                    data["type"] as QuestType,
                    data["beforeQuest"] as String,
                    data["startNpc"] as String,
                    data["endNpc"] as String,
                    data["talks"] as List<String>,
                    data["acceptMessage"] as String,
                    data["disposeMessage"] as String,
                    data["purposeMessage"] as String,
                    data["completeMessage"] as String,
                    data["action"] as QuestAction,
                    data["stringObject"] as String,
                    data["locationObject"] as Location,
                    data["itemStackObject"] as ItemStack,
                    data["count"] as Int,
                    data["rewardItems"] as List<ItemStack>,
                    data["rewardCommand"] as String
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "name" to name,
                "title" to title,
                "limitLv" to limitLv,
                "type" to type,
                "beforeQuest" to beforeQuest,
                "startNpc" to startNpc,
                "endNpc" to endNpc,
                "talks" to talks,
                "acceptMessage" to acceptMessage,
                "disposeMessage" to disposeMessage,
                "purposeMessage" to purposeMessage,
                "completeMessage" to completeMessage,
                "action" to action,
                "stringObject" to stringObject,
                "locationObject" to locationObject,
                "itemStackObject" to itemStackObject,
                "count" to count,
                "rewardItems" to rewardItems,
                "rewardCommand" to rewardCommand
        )
    }

    fun sendAcceptMessage(player: Player) {
        player.sendInfoMessage("$startNpc : $acceptMessage")
    }

    fun sendDisposeMessage(player: Player) {
        player.sendInfoMessage("$startNpc : $disposeMessage")
    }

    fun sendPurposeMessage(player: Player) {
        player.sendInfoMessage("$startNpc : $purposeMessage")
    }

    fun sendCompleteMessage(player: Player) {
        player.sendInfoMessage("$endNpc : $completeMessage")
    }

    fun toItemStack(): ItemStack {
        return createItemStack(Material.BOOK_AND_QUILL, "&f$title", talks, 1, 0)
    }

    fun toItemStackWithPlayerQuest(playerQuest: PlayerQuest): ItemStack {
        return when {
            playerQuest.inProgressQuestList.containsKey(name) ->
                createItemStack(Material.BOOK, "&f${title}", listOf("&e진행중인 퀘스트 입니다."))
            playerQuest.completeQuestList.contains(name) ->
                createItemStack(Material.ENCHANTED_BOOK, "&f${title}", listOf("&8이미 완료한 퀘스트 입니다."))
            else ->
                createItemStack(Material.BOOK_AND_QUILL, "&f${title}", listOf("&c수행하지 않은 퀘스트 입니다."))
        }
    }

}