package com.namu.core.economy.auction.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.economy.auction.model.AuctionRepository
import com.namu.core.economy.auction.model.entity.AuctionStuff
import com.namu.core.economy.money.model.entity.CheckPaper
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class CommandAddAuctionStuff : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "손에 들고 있는 아이템을 경매장에 등록합니다."

    override val usage: String = "<Price>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player

        val itemInHand = player.inventory.itemInMainHand

        if (itemInHand.type == Material.AIR) {
            player.sendErrorMessage("손에 아이템을 소지하고 있지 않습니다.")
            return true
        }

        if (isBlackListTag(itemInHand)) {
            player.sendErrorMessage("해당 아이템은 경매장에 등록 할 수 없습니다.")
            return true
        }

        AuctionRepository.addAuctionStuff(
                AuctionStuff(
                        item = itemInHand,
                        ownerUuid = player.uniqueId.toString(),
                        price = args.nextInt(),
                        uuid = UUID.randomUUID().toString()
                )
        )

        player.inventory.setItemInMainHand(null)
        player.sendInfoMessage("정상적으로 아이템이 등록되었습니다.")

        AuctionRepository.saveAuction()
        return true
    }

    private fun isBlackListTag(item: ItemStack): Boolean {
        return item.getNBTTagCompound(CheckPaper::class.java) != null
    }

}