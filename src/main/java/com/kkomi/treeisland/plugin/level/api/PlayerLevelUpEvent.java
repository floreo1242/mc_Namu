package com.kkomi.treeisland.plugin.level.api;

import com.kkomi.treeisland.plugin.integration.PlayerInfo;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerLevelUpEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private PlayerInfo playerInfo;

    public PlayerLevelUpEvent(@NotNull PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerLevelUpEvent(boolean isAsync, @NotNull PlayerInfo playerInfo) {
        super(isAsync);
        this.playerInfo = playerInfo;
    }

    @NotNull
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList()  {
        return handlers;
    }

}