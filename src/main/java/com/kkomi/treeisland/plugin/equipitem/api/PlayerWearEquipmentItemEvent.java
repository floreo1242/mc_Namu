package com.kkomi.treeisland.plugin.equipitem.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerWearEquipmentItemEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private Player player;

    public PlayerWearEquipmentItemEvent(@NotNull Player player) {
        this.player = player;
    }

    public PlayerWearEquipmentItemEvent(boolean isAsync, @NotNull Player player) {
        super(isAsync);
        this.player = player;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}