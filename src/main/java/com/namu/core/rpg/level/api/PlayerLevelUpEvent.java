package com.namu.core.rpg.level.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerLevelUpEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private Player player;

    public PlayerLevelUpEvent(@NotNull Player player) {
        this.player = player;
    }

    public PlayerLevelUpEvent(boolean isAsync, @NotNull Player player) {
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

}