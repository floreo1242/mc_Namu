package com.kkomi.treeisland.plugin.guild.listener

import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerGuildListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        val playerGuild = PlayerGuildRepository.getPlayerGuild(player.uniqueId.toString())

        if (playerGuild == null) {
            PlayerGuildRepository.addPlayerGuild(PlayerGuild(player.uniqueId.toString(), ""))
        }
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        val guildPlayer = PlayerGuildRepository.getPlayerGuild(event.player.uniqueId.toString()) ?: return

        if (guildPlayer.guildName.isEmpty()) return

        event.format = "[${guildPlayer.guildName}] %1s %2s"
    }
}