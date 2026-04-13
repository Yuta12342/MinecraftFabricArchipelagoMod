package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class RandomEffectTrap extends StatusEffectTrap {
    private static final List<StatusEffect> EFFECTS = Registries.STATUS_EFFECT.stream()
            .filter(effect -> effect != null && !effect.isBeneficial())
            .toList();

    public RandomEffectTrap() {
        super(StatusEffects.POISON, 250);
    }

    @Override
    protected StatusEffect getEffect(ServerPlayerEntity player) {
        return EFFECTS.get(player.getRandom().nextInt(EFFECTS.size()));
    }
}
