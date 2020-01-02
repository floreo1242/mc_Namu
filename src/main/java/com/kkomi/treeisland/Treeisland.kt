package com.kkomi.treeisland

import com.kkomi.treeisland.plugin.itemdb.ItemDBPlugin
import com.kkomi.treeisland.plugin.money.MoneyPlugin
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.stat.StatPlugin
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.talkscript.TalkScriptPlugin
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Treeisland : JavaPlugin(), Listener {

    companion object {
        lateinit var instance: Treeisland

        lateinit var moneyPlugin: MoneyPlugin
        lateinit var shopPlugin: ShopPlugin
        lateinit var questPlugin: QuestPlugin
        lateinit var talkScriptPlugin: TalkScriptPlugin
        lateinit var itemDbPlugin: ItemDBPlugin
        lateinit var statPlugin: StatPlugin
    }

    override fun onEnable() {
        instance = this

        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
        questPlugin = QuestPlugin(File(dataFolder.path + "/quest"), this)
        talkScriptPlugin = TalkScriptPlugin(File(dataFolder.path + "/talkscript"), this)
        itemDbPlugin = ItemDBPlugin(File(dataFolder.path + "/itemdb"), this)
        statPlugin = StatPlugin(File(dataFolder.path + "/stat"), this)
    }

    override fun onDisable() {
        moneyPlugin.onDisable()
        shopPlugin.onDisable()
        questPlugin.onDisable()
        talkScriptPlugin.onDisable()
        itemDbPlugin.onDisable()
        statPlugin.onDisable()
    }

}