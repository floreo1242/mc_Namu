package com.namu.core.rpg.skill.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SpellApplyDamagePlayerByEntityEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private Player caster;
    @NotNull
    private LivingEntity entity;
    @NotNull
    private Double originDamage;

    public SpellApplyDamagePlayerByEntityEvent(@NotNull Player caster, @NotNull LivingEntity entity, @NotNull Double originDamage) {
        this.caster = caster;
        this.entity = entity;
        this.originDamage = originDamage;
    }

    public SpellApplyDamagePlayerByEntityEvent(boolean isAsync, @NotNull Player caster, @NotNull LivingEntity entity, @NotNull Double originDamage) {
        super(isAsync);
        this.caster = caster;
        this.entity = entity;
        this.originDamage = originDamage;
    }

    @NotNull
    public Player getCaster() {
        return caster;
    }

    @NotNull
    public LivingEntity getEntity() {
        return entity;
    }

    @NotNull
    public Double getOriginDamage() {
        return originDamage;
    }

    @NotNull
    public Double getPercent() {
        return originDamage / 100D;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}