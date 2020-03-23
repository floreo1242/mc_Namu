package com.namu.core

import com.namu.core.economy.auction.AuctionPlugin
import com.namu.core.economy.money.MoneyPlugin
import com.namu.core.economy.shop.ShopPlugin
import com.namu.core.life.maker.MakerPlugin
import com.namu.core.life.npc.NpcPlugin
import com.namu.core.rpg.guild.GuildPlugin
import com.namu.core.utility.itemdb.ItemDBPlugin
import com.namu.core.rpg.level.LevelPlugin
import com.namu.core.rpg.monster.MonsterPlugin
import com.namu.core.rpg.quest.QuestPlugin
import com.namu.core.rpg.role.RolePlugin
import com.namu.core.rpg.skill.SkillPlugin
import com.namu.core.rpg.stat.StatPlugin
import com.namu.core.utility.bag.BagPlugin
import com.namu.core.utility.post.PostPlugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MainCore : JavaPlugin() {
    companion object {
        lateinit var instance: MainCore

        lateinit var moneyPlugin: MoneyPlugin
        lateinit var shopPlugin: ShopPlugin
        lateinit var auctionPlugin: AuctionPlugin

        lateinit var makerPlugin : MakerPlugin
        lateinit var npcPlugin : NpcPlugin

        lateinit var questPlugin: QuestPlugin
        lateinit var statPlugin: StatPlugin
        lateinit var levelPlugin: LevelPlugin
        lateinit var monsterPlugin: MonsterPlugin
        lateinit var guildPlugin: GuildPlugin
        lateinit var skillPlugin: SkillPlugin
        lateinit var rolePlugin: RolePlugin

        lateinit var bagPlugin : BagPlugin
        lateinit var itemDbPlugin: ItemDBPlugin
        lateinit var postPlugin: PostPlugin
    }

    override fun onEnable() {
        instance = this

        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        logger.info("Load complete money plugin...!")

        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
        logger.info("Load complete shop plugin...!")

        auctionPlugin = AuctionPlugin(File(dataFolder.path + "/auction"), this)
        logger.info("Load complete shop plugin...!")

        questPlugin = QuestPlugin(File(dataFolder.path + "/quest"), this)
        logger.info("Load complete quest plugin...!")

        itemDbPlugin = ItemDBPlugin(File(dataFolder.path + "/itemdb"), this)
        logger.info("Load complete item db plugin...!")

        statPlugin = StatPlugin(File(dataFolder.path + "/stat"), this)
        logger.info("Load complete stat plugin...!")

        levelPlugin = LevelPlugin(File(dataFolder.path + "/level"), this)
        logger.info("Load complete level plugin...!")

        monsterPlugin = MonsterPlugin(File(dataFolder.path + "/monster"), this)
        logger.info("Load complete monster plugin...!")

        guildPlugin = GuildPlugin(File(dataFolder.path + "/guild"), this)
        logger.info("Load complete guild plugin...!")

        skillPlugin = SkillPlugin(File(dataFolder.path + "/skill"), this)
        logger.info("Load complete skill plugin...!")

        rolePlugin = RolePlugin(File(dataFolder.path + "/role"), this)
        logger.info("Load complete role plugin...!")

        bagPlugin = BagPlugin(File(dataFolder.path + "/bag"), this)
        logger.info("Load complete bag plugin...!")

        postPlugin = PostPlugin(File(dataFolder.path + "/post"), this)
        logger.info("Load complete post plugin...!")

        makerPlugin = MakerPlugin(File(dataFolder.path + "/maker"), this)
        logger.info("Load complete post plugin...!")

        npcPlugin = NpcPlugin(File(dataFolder.path + "/npc"), this)
        logger.info("Load complete post plugin...!")

    }

    override fun onDisable() {
        moneyPlugin.onDisable()
        shopPlugin.onDisable()
        questPlugin.onDisable()
        itemDbPlugin.onDisable()
        statPlugin.onDisable()
        levelPlugin.onDisable()
        monsterPlugin.onDisable()
        guildPlugin.onDisable()
        skillPlugin.onDisable()
        rolePlugin.onDisable()
        bagPlugin.onDisable()
        postPlugin.onDisable()
        makerPlugin.onDisable()
        npcPlugin.onDisable()
    }
}