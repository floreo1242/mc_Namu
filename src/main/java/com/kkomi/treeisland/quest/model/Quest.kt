package com.kkomi.treeisland.quest.model

import com.kkomi.library.extension.*
import com.kkomi.treeisland.quest.QuestManager
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File

data class Quest(
        val manager: QuestManager,
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
) {

    constructor(
            manager: QuestManager,
            config: YamlConfiguration
    ) : this(
            manager,
            config.getString("name"),
            config.getString("title"),
            config.getInt("limitLv"),
            QuestType.valueOf(config.getString("type")),
            config.getString("beforeQuest"),
            config.getString("startNpc"),
            config.getString("endNpc"),
            config.getStringList("talks"),
            config.getString("acceptMessage"),
            config.getString("disposeMessage"),
            config.getString("purposeMessage"),
            config.getString("completeMessage"),
            QuestAction.valueOf(config.getString("action")),
            config.getString("stringObject"),
            config.getSerializable("locationObject", Location::class.java),
            config.getItemStack("itemStackObject"),
            config.getInt("count"),
            config.getItemStackList("rewardItems"),
            config.getString("rewardCommand")
    )

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

    private val file: File
        get() {
            manager.folder.mkdir()
            return File(manager.folder, "$name${manager.EXT}")
        }

    fun save() {
        val config = YamlConfiguration.loadConfiguration(file)

        config.set("name", name)
        config.set("title", title)
        config.set("limitLv", limitLv)
        config.set("type", type.toString())
        config.set("beforeQuest", beforeQuest)
        config.set("startNpc", startNpc)
        config.set("endNpc", endNpc)
        config.set("talks", talks)
        config.set("acceptMessage", acceptMessage)
        config.set("disposeMessage", disposeMessage)
        config.set("purposeMessage", purposeMessage)
        config.set("completeMessage", completeMessage)
        config.set("action", action.toString())
        config.set("stringObject", stringObject)
        config.set("locationObject", locationObject)
        config.set("itemStackObject", itemStackObject)
        config.set("count", count)
        config.setItemStackList("rewardItems", rewardItems)
        config.set("rewardCommand", rewardCommand)

        config.save(file)
    }

    fun remove() {
        manager.justRemove(name)
        file.delete()
    }

}