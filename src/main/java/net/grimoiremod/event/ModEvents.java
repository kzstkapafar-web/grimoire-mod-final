package net.grimoiremod.event;

import net.grimoiremod.GrimoireMod;
import net.grimoiremod.entity.ModEntityTypes;
import net.grimoiremod.entity.WraithEntity;
import net.grimoiremod.entity.client.WraithRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = GrimoireMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonEvents {
        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.WRAITH.get(), WraithEntity.createAttributes().build());
        }
    }

    @Mod.EventBusSubscriber(modid = GrimoireMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.WRAITH.get(), WraithRenderer::new);
        }
    }
}
