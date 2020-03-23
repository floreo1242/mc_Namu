package com.namu.core.life.maker.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.life.maker.inventory.RecipeListInventory
import com.namu.core.life.maker.model.MakerCategoryRepository
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.MakerCategory
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.model.entity.RecipeMaterial
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenRecipeListInventory : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "open recipe list"

    override val usage: String = "<categoryName>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val makerCategory = MakerCategoryRepository.getMakerCategory(args.next())

        if (makerCategory == null) {
            sender.sendErrorMessage("존재하지 않는 카테고리 입니다.")
            return true
        }

        RecipeListInventory(sender as Player, makerCategory.name).open()
        return true
    }

}