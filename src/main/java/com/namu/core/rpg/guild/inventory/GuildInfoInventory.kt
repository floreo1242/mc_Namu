package com.namu.core.rpg.guild.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.rpg.guild.model.GuildOptionRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildMemberState
import com.namu.core.rpg.level.util.playerLevel
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.OfflinePlayer
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
                Material.OAK_SIGN,
                "&f길드 정보",
                listOf(
                        "",
                        "&f길드 이름 : ${guild.name}",
                        "&f길드 레벨 : ${guild.level}",
                        "&f길드 기여 : ${guild.guildPoint}",
                        "&f길드 인원 : ${guild.members.size} / $maxMember"
                )
        )

        inventory.setItem(4, guildInfo)
        for (i in 18..40) {

            if (i - 18 >= guild.members.size) {
                return
            }

            val guildMemberUUID = guild.members.keys.toList()[i - 18]
            val offlinePlayerInfo = Bukkit.getOfflinePlayer(UUID.fromString(guildMemberUUID))

            inventory.setItem(i, guildMemberToItemStack(offlinePlayerInfo, guild.members[guildMemberUUID]!!))
        }
    }

    private fun guildMemberToItemStack(offlinePlayer: OfflinePlayer, guildMemberState: GuildMemberState): ItemStack {
        return createItemStack(
                Material.SKELETON_SKULL,
                "&f${offlinePlayer.name}",
                listOf(
                        "&f",
                        "&f직급 : ${guildMemberState.grade.strName}",
                        "&f레벨 : &e${offlinePlayer.playerLevel?.level}",
                        "&f기여도 : &b${DecimalFormat("#,##0").format(guildMemberState.contribution)}"
                ),
                durability = 3
        ).apply {
            val skullMeta = itemMeta as SkullMeta
            skullMeta.owningPlayer = offlinePlayer
            itemMeta = skullMeta
        }
    }

}