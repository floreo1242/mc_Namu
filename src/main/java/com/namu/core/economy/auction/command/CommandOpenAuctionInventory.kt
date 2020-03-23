package com.namu.core.economy.auction.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.economy.auction.inventory.AuctionInventory
import com.namu.core.economy.auction.model.AuctionRepository
import com.namu.core.economy.auction.model.entity.AuctionStuff
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class CommandOpenAuctionInventory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "경매장을 확인합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player
        AuctionInventory(player).open()
        return true
    }

}