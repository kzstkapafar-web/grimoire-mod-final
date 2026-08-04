package net.grimoiremod.entity;

import net.grimoiremod.GrimoireMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GrimoireMod.MODID);

    public static final RegistryObject<EntityType<WraithEntity>> WRAITH = ENTITY_TYPES.register("wraith",
            () -> EntityType.Builder.of(WraithEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build("wraith"));
}
