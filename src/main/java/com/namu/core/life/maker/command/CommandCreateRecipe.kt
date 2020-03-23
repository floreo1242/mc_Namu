package com.namu.core.life.maker.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.model.entity.RecipeMaterial
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateRecipe : CommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "reload recipe"

    override val usage: String = "<recipeName> <categoryName> "

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val recipeName = args.next()
        val categoryName = args.next()
        RecipeRepository.createRecipe(Recipe(categoryName, listOf(RecipeMaterial(1, "empty")), recipeName, "empty"))
        sender.sendInfoMessage("레시피를 생성하였습니다.")
        return true
    }

}