package com.namu.core.life.maker

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.namu.core.economy.shop.inventory.ShopInventory
import com.namu.core.life.maker.command.*
import com.namu.core.life.maker.inventory.MakingInventory
import com.namu.core.life.maker.inventory.RecipeListInventory
import com.namu.core.life.maker.listener.MakingInventoryListener
import com.namu.core.life.maker.listener.PlayerRecipeListener
import com.namu.core.life.maker.listener.RecipeListInventoryListener
import com.namu.core.life.maker.model.entity.MakerCategory
import com.namu.core.life.maker.model.entity.PlayerRecipe
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.model.entity.RecipeMaterial
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MakerPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("open", CommandOpenRecipeListInventory())
        }.register(plugin.getCommand("maker"))

        CommandManager(true).apply {
            addComponent("create", CommandCreateRecipe())
            addComponent("delete", CommandDeleteRecipe())
            addComponent("get", CommandGetRecipe())
            addComponent("reload", CommandReloadRecipe())
        }.register(plugin.getCommand("makera"))

        CommandManager(true).apply {
            addComponent("create", CommandCreateMakerCategory())
            addComponent("delete", CommandDeleteMakerCategory())
            addComponent("reload", CommandReloadMakerCategory())
        }.register(plugin.getCommand("categorya"))
    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(RecipeListInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(MakingInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerRecipeListener(), plugin)
            registerEvents(MakingInventoryListener(), plugin)
            registerEvents(RecipeListInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(MakerCategory::class.java, "MakerCategory")
        ConfigurationSerialization.registerClass(PlayerRecipe::class.java, "PlayerRecipe")
        ConfigurationSerialization.registerClass(Recipe::class.java, "Recipe")
        ConfigurationSerialization.registerClass(RecipeMaterial::class.java, "RecipeMaterial")
    }

    override fun setupSchedulers() {
    }
}