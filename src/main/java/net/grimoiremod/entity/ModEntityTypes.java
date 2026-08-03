package net.grimoiremod.entity;

import net.grimoiremod.GrimoireMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredHolder;
import net.minecraftforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, GrimoireMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<WraithEntity>> WRAITH =
            ENTITY_TYPES.register("wraith", () -> EntityType.Builder.of(WraithEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build("wraith"));
}
