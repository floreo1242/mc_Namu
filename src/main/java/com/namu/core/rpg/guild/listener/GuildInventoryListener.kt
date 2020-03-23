package com.namu.core.rpg.guild.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.namu.core.rpg.guild.inventory.GuildInfoInventory
import com.namu.core.rpg.guild.model.GuildRepository
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class GuildInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != GuildInfoInventory.TITLE) {
            return
        }

        val guild = GuildRepository.getGuild(data.second)!!
        event.isCancelled = true
    }
 }