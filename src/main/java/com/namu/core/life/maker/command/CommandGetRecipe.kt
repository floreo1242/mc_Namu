package com.namu.core.life.maker.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.model.entity.RecipeMaterial
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetRecipe : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "reload recipe"

    override val usage: String = "<recipeName>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val recipeName = args.next()
        val recipe = RecipeRepository.getRecipe(recipeName)

        if (recipe == null) {
            sender.sendErrorMessage("존재하지 않는 레시피 입니다.")
            return true
        }

        (sender as Player).inventory.addItem(recipe.toItemStack())
        sender.sendInfoMessage("레시피가 발급되었습니다.")
        return true
    }

}