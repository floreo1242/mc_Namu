package com.kkomi.treeisland.plugin.skill.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EntityDeathBySpellCasterEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private Player caster;
    @NotNull
    private LivingEntity entity;

    public EntityDeathBySpellCasterEvent(@NotNull Player caster, @NotNull LivingEntity entity) {
        this.caster = caster;
        this.entity = entity;
    }

    public EntityDeathBySpellCasterEvent(boolean isAsync, @NotNull Player caster, @NotNull LivingEntity entity) {
        super(isAsync);
        this.caster = caster;
        this.entity = entity;
    }

    @NotNull
    public Player getCaster() {
        return caster;
    }

    @NotNull
    public LivingEntity getEntity() {
        return entity;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}