package com.namu.core.life.maker.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.model.entity.RecipeMaterial
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandDeleteRecipe : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "reload recipe"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val categoryName = args.next()
        val recipeName = args.next()
        RecipeRepository.createRecipe(Recipe(categoryName, listOf(RecipeMaterial(1, "empty")), recipeName, "empty"))
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}