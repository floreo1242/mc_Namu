package com.namu.core.rpg.guild.model.entity

import org.bukkit.entity.Player

class GuildInviteRequest(
        val from: Player,
        val to: Player,
        val guild: Guild
)