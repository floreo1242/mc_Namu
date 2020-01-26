package com.kkomi.treeisland

import com.kkomi.treeisland.plugin.equipitem.EquipItemPlugin
import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import com.kkomi.treeisland.plugin.guild.GuildPlugin
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.ItemDBPlugin
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.level.LevelPlugin
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.money.MoneyPlugin
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.monster.MonsterPlugin
import com.kkomi.treeisland.plugin.monster.model.MonsterRepository
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.role.RolePlugin
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.RoleRepository
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.skill.SkillPlugin
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.stat.StatPlugin
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

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
        lateinit var rolePlugin : RolePlugin
    }

    override fun onEnable() {
        instance = this

        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        PlayerMoneyRepository.reloadPlayerMoney()
        logger.info("Load complete money plugin...!")

        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
        ShopRepository.reloadShop()
        logger.info("Load complete shop plugin...!")

        questPlugin = QuestPlugin(File(dataFolder.path + "/quest"), this)
        QuestRepository.reloadQuest()
        PlayerQuestRepository.reloadPlayerQuest()
        logger.info("Load complete quest plugin...!")

        itemDbPlugin = ItemDBPlugin(File(dataFolder.path + "/itemdb"), this)
        EquipmentItemRepository.reloadItems()
        ConsumptionItemRepository.reloadItems()
        OtherItemRepository.reloadItems()
        logger.info("Load complete item db plugin...!")

        statPlugin = StatPlugin(File(dataFolder.path + "/stat"), this)
        StatConfigRepository.reloadStatConfig()
        PlayerStatRepository.reloadPlayerStat()
        logger.info("Load complete stat plugin...!")

        levelPlugin = LevelPlugin(File(dataFolder.path + "/level"), this)
        PlayerLevelRepository.reloadPlayerLevel()
        LevelConfigRepository.reloadLevelConfig()
        logger.info("Load complete level plugin...!")

        monsterPlugin = MonsterPlugin(File(dataFolder.path + "/monster"), this)
        MonsterRepository.reloadMonster()
        logger.info("Load complete monster plugin...!")

        guildPlugin = GuildPlugin(File(dataFolder.path + "/guild"), this)
        GuildRepository.reloadGuild()
        PlayerGuildRepository.reloadPlayerGuild()
        logger.info("Load complete guild plugin...!")

        equipItemPlugin = EquipItemPlugin(File(dataFolder.path + "/equipitem"), this)
        PlayerEquipItemRepository.reloadPlayerEquipItem()
        logger.info("Load complete equip item plugin...!")

        skillPlugin = SkillPlugin(File(dataFolder.path + "/skill"), this)
        PlayerSkillInfoRepository.reloadPlayerSkillInfo()
        SkillInfoRepository.reloadSkillInfo()
        logger.info("Load complete skill plugin...!")

        rolePlugin = RolePlugin(File(dataFolder.path + "/role"), this)
        RoleRepository.reloadRole()
        PlayerRoleRepository.reloadPlayerRole()
        logger.info("Load complete role plugin...!")

        Bukkit.getOnlinePlayers()
                .forEach {
                    it.getPlayerInfo().statInfo.updateFinalStat(it.getPlayerInfo().equipmentInfo)
                    it.getPlayerInfo().statInfo.calculateStatOption(it)
                }
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
    }

}