package com.kkomi.treeisland.money

import com.kkomi.library.ObjectManager
import com.kkomi.treeisland.money.entity.PlayerMoney
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.util.*
import java.util.logging.Logger

class MoneyManager(folder: File, logger: Logger) : ObjectManager<PlayerMoney>(folder, logger) {

    companion object {
        const val DEFAULT_MONEY = 1000
    }

    override fun loadData() {
        val playerMoneyByUUID = TreeMap<String, PlayerMoney>(String.CASE_INSENSITIVE_ORDER)
        getFolderFiles().forEach { file ->
            val uuid = file.name.substring(0, file.name.length - EXTlen)
            try {
                val config = YamlConfiguration.loadConfiguration(file)
                val playerMoney = PlayerMoney(this, config)
                playerMoneyByUUID[playerMoney.uuid] = playerMoney
            } catch (exception: Exception) {
                logger.info("Failed to load from $uuid cause : ")
                exception.printStackTrace()
            }
        }
        println("Load File Count : ${playerMoneyByUUID.keys.count()}")
        this.objectByName = playerMoneyByUUID
    }

    fun createPlayerMoney(player: Player): PlayerMoney {
        val playerMoney = PlayerMoney(this, player.uniqueId.toString(), DEFAULT_MONEY)
        objectByName[playerMoney.uuid] = playerMoney
        playerMoney.save()
        this.objectList = null
        return playerMoney
    }

    fun getPlayerMoney(uuid: String): PlayerMoney? = objectByName[uuid]

    val uuids: Set<String> = keys!!

    val playerMoneyList: List<PlayerMoney> = objectList!!

    val playerMoneys: Collection<PlayerMoney> = objects!!

}