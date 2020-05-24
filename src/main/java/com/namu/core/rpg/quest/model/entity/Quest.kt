package com.namu.core.rpg.quest.model.entity

import com.kkomi.devlibrary.extension.*
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@SerializableAs("Quest")
data class Quest(
        val name: String,
        var title: String,
        var limitLv: Int,
        var type: QuestType,
        var beforeQuest: String,
        var startNpc: String,
        var endNpc: String,
        var description : String,
        var talkScriptList: List<TalkScript>,
        var acceptMessage: String,
        var disposeMessage: String,
        var purposeMessage: String,
        var completeMessage: String,
        var questObjectiveList : List<QuestObjective>,
        var reward : QuestReward
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Quest {
            return Quest(
                    data["name"] as String,
                    data["title"] as String,
                    data["limitLv"] as Int,
                    QuestType.valueOf(data["type"] as String),
                    data["beforeQuest"] as String,
                    data["startNpc"] as String,
                    data["endNpc"] as String,
                    data["description"] as String,
                    data["talkScriptList"] as List<TalkScript>,
                    data["acceptMessage"] as String,
                    data["disposeMessage"] as String,
                    data["purposeMessage"] as String,
                    data["completeMessage"] as String,
                    data["questObjectiveList"] as List<QuestObjective>,
                    data["reward"] as QuestReward
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "name" to name,
                "title" to title,
                "limitLv" to limitLv,
                "type" to type.name,
                "beforeQuest" to beforeQuest,
                "startNpc" to startNpc,
                "endNpc" to endNpc,
                "description" to description,
                "talkScriptList" to talkScriptList,
                "acceptMessage" to acceptMessage,
                "disposeMessage" to disposeMessage,
                "purposeMessage" to purposeMessage,
                "completeMessage" to completeMessage,
                "questObjectiveList" to questObjectiveList,
                "reward" to reward
        )
    }

    fun sendAcceptMessage(player: Player) {
        if (acceptMessage.isEmpty()) {
            return
        }
        player.sendInfoMessage("$startNpc : $acceptMessage")
    }

    fun sendDisposeMessage(player: Player) {
        if (disposeMessage.isEmpty()) {
            return
        }
        player.sendInfoMessage("$startNpc : $disposeMessage")
    }

    fun sendPurposeMessage(player: Player) {
        if (purposeMessage.isEmpty()) {
            return
        }
        player.sendInfoMessage("$startNpc : $purposeMessage")
    }

    fun sendCompleteMessage(player: Player) {
        if (completeMessage.isEmpty()) {
            return
        }
        player.sendInfoMessage("$endNpc : $completeMessage")
    }

    fun toItemStack() : ItemStack {
        return createItemStack(
                Material.PAPER,
                "&f퀘스트 설명",
                description.split("|").map { "&f$it" }
        )
    }

    fun toItemStackWithPlayerQuest(playerQuest: PlayerQuest): ItemStack {
        return when {
            playerQuest.inProgressQuestList.containsKey(name) ->
                createItemStack(Material.BOOK, "&f${title}", listOf("&e진행중인 퀘스트 입니다."))
            playerQuest.completeQuestList.contains(name) ->
                createItemStack(Material.ENCHANTED_BOOK, "&f${title}", listOf("&8이미 완료한 퀘스트 입니다."))
            else ->
                createItemStack(Material.WRITABLE_BOOK, "&f${title}", listOf("&c수행하지 않은 퀘스트 입니다."))
        }
    }

}