package com.kkomi.treeisland.plugin.guild.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.guild.model.GuildOptionRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.text.DecimalFormat
import java.util.*

class GuildInfoInventory(
        player: Player,
        val guild: Guild
) : InventoryManager(player) {

    companion object {
        const val TITLE = "길드정보"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, "$TITLE - ${guild.name}")

    override fun setBasicFrame() {
        val maxMember: Int = GuildOptionRepository.let { it.getDefaultMaxMember() + it.getUpgradeMemberCount() * guild.level }
        val guildInfo = createItemStack(
                Material.SIGN,
                "&f길드 정보",
                listOf(
                        "",
                        "&f길드 이름 : ${guild.name}",
                        "&f길드 레벨 : ${guild.level}",
                        "&f길드 기여 : ${guild.guildPoint}",
                        "&f길드 인원 : ${guild.members.size} / $maxMember}"
                )
        )

        inventory.setItem(4, guildInfo)
        for (i in 18..40) {

            if (i - 18 >= guild.members.size) {
                return
            }

            val guildMemberUUID = guild.members.keys.toList()[i - 18]
            val offlinePlayerInfo = OfflinePlayerInfo(Bukkit.getOfflinePlayer(UUID.fromString(guildMemberUUID)))

            inventory.setItem(i, guildMemberToItemStack(offlinePlayerInfo, guild.members[guildMemberUUID]!!))
        }
    }

    private fun guildMemberToItemStack(playerInfo: OfflinePlayerInfo, guildMemberState: GuildMemberState): ItemStack {
        return createItemStack(
                Material.SKULL_ITEM,
                "&f${playerInfo.offlinePlayer.name}",
                listOf(
                        "&f",
                        "&f직급 : ${guildMemberState.grade.strName}",
                        "&f레벨 : &e${playerInfo.levelInfo.level}",
                        "&f기여도 : &b${DecimalFormat("#,##0").format(guildMemberState.contribution)}"
                ),
                durability = 3
        ).apply {
            val skullMeta = itemMeta as SkullMeta
            skullMeta.owningPlayer = playerInfo.offlinePlayer
            itemMeta = skullMeta
        }
    }

}