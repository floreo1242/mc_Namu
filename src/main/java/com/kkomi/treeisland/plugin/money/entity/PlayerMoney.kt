package com.kkomi.treeisland.plugin.money.entity

import com.kkomi.treeisland.plugin.money.MoneyManager
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

data class PlayerMoney(
        val manager: MoneyManager,
        val uuid: String,
        var money: Int
) {

    constructor(manager: MoneyManager, config: YamlConfiguration) : this(manager, config.getString("uuid"), config.getInt("money"))

    fun giveMoney(money: Int) {
        this.money += money
    }

    fun takeMoney(money: Int) {
        this.money -= money
    }

    private val file : File
        get() {
            manager.folder.mkdirs()
            return File(manager.folder, "$uuid${manager.EXT}")
        }

    fun save() {
        val config = YamlConfiguration.loadConfiguration(file)
        config.set("uuid", uuid)
        config.set("money", money)
        config.save(file)
    }

    fun remove() {
        manager.justRemove(uuid)
        file.delete()
    }

}