package com.namu.core.rpg.level.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerExpGetEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private Boolean isCancelled = false;

    @NotNull
    private Player player;

    public PlayerExpGetEvent(@NotNull Player player) {
        this.player = player;
    }

    public PlayerExpGetEvent(boolean isAsync, @NotNull Player player) {
        super(isAsync);
        this.player = player;
    }

    @NotNull
    public Player getPlayer() {
        return player;
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