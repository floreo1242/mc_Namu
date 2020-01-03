package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildInviteRequest
import org.bukkit.entity.Player

object GuildInviteRequestRepository {

    private val guildInviteRequest: MutableList<GuildInviteRequest> = mutableListOf()

    fun get(player: Player): GuildInviteRequest? {
        return guildInviteRequest.find { it.to.uniqueId.toString() == player.uniqueId.toString() }
    }

    fun add(from: Player, to: Player, guild: Guild) {
        guildInviteRequest.add(GuildInviteRequest(from, to, guild))
    }

    fun removeFrom(from: Player) {
        guildInviteRequest.removeIf { it.from.uniqueId.toString() == from.uniqueId.toString() }
    }

    fun removeTo(to: Player) {
        guildInviteRequest.removeIf { it.to.uniqueId.toString() == to.uniqueId.toString() }
    }

    fun removeGuild(guild: Guild) {
        guildInviteRequest.removeIf { it.guild.name == guild.name }
    }

}