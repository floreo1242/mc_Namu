package com.kkomi.treeisland.plugin.level.api;

import com.kkomi.treeisland.plugin.integration.PlayerInfo;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerExpGetEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private Boolean isCancelled = false;

    @NotNull
    private PlayerInfo playerInfo;

    public PlayerExpGetEvent(@NotNull PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerExpGetEvent(boolean isAsync, @NotNull PlayerInfo playerInfo) {
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

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
}