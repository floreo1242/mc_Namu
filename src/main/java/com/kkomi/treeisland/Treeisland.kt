package com.kkomi.treeisland

import com.kkomi.treeisland.plugin.bag.BagPlugin
import com.kkomi.treeisland.plugin.enhance.EnhancePlugin
import com.kkomi.treeisland.plugin.enhance.model.EnhanceStone
import com.kkomi.treeisland.plugin.equipitem.EquipItemPlugin
import com.kkomi.treeisland.plugin.guild.GuildPlugin
import com.kkomi.treeisland.plugin.integration.IntegrationPlugin
import com.kkomi.treeisland.plugin.integration.scheduler.PlayerDataScheduler
import com.kkomi.treeisland.plugin.itemdb.ItemDBPlugin
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.level.LevelPlugin
import com.kkomi.treeisland.plugin.money.MoneyPlugin
import com.kkomi.treeisland.plugin.monster.MonsterPlugin
import com.kkomi.treeisland.plugin.post.PostPlugin
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.role.RolePlugin
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.skill.SkillPlugin
import com.kkomi.treeisland.plugin.skill.scheduler.SpellCooldownSchedulers
import com.kkomi.treeisland.plugin.stat.StatPlugin
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File


@Suppress("UnstableApiUsage")
class Treeisland : JavaPlugin(), Listener {

    companion object {
        lateinit var instance: Treeisland

        lateinit var moneyPlugin: MoneyPlugin
        lateinit var shopPlugin: ShopPlugin
        lateinit var questPlugin: QuestPlugin
        lateinit var itemDbPlugin: ItemDBPlugin
        lateinit var statPlugin: StatPlugin
        lateinit var levelPlugin: LevelPlugin
        lateinit var monsterPlugin: MonsterPlugin
        lateinit var guildPlugin: GuildPlugin
        lateinit var equipItemPlugin: EquipItemPlugin
        lateinit var skillPlugin: SkillPlugin
        lateinit var rolePlugin: RolePlugin
        lateinit var bagPlugin : BagPlugin
        lateinit var postPlugin: PostPlugin
    }

    override fun onEnable() {
        instance = this

        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        logger.info("Load complete money plugin...!")

        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
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

        equipItemPlugin = EquipItemPlugin(File(dataFolder.path + "/equipitem"), this)
        logger.info("Load complete equip item plugin...!")

        skillPlugin = SkillPlugin(File(dataFolder.path + "/skill"), this)
        logger.info("Load complete skill plugin...!")

        rolePlugin = RolePlugin(File(dataFolder.path + "/role"), this)
        logger.info("Load complete role plugin...!")

        bagPlugin = BagPlugin(File(dataFolder.parent + "/bag"), this)
        logger.info("Load complete bag plugin...!")

        postPlugin = PostPlugin(File(dataFolder.parent + "/post"), this)
        logger.info("Load complete post plugin...!")

        IntegrationPlugin(File(dataFolder.path + "/integration"), this)
        EnhancePlugin(File(dataFolder.parent + "/scroll"), this)

        PlayerDataScheduler().setPlugin(this).startTimer(0, -1, 0, 0)
        SpellCooldownSchedulers().setPlugin(this).startTimer(0, -1, 0, 20)

        println(EnhanceStone(StatOption.VITALITY, 10, 40, 20).toItemStack())
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
        equipItemPlugin.onDisable()
        skillPlugin.onDisable()
        rolePlugin.onDisable()
        bagPlugin.onDisable()
        postPlugin.onDisable()
    }

}