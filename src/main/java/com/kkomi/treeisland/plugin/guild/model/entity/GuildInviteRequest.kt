package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.entity.Player

class GuildInviteRequest(
        val from: Player,
        val to: Player,
        val guild: Guild
)