package net.grimoiremod.item;

import net.grimoiremod.GrimoireMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, GrimoireMod.MODID);

    public static final RegistryObject<CreativeModeTab> GRIMOIRE_TAB = TABS.register("grimoire_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.grimoire"))
                    .icon(() -> ModItems.GRIMOIRE_BOOK.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RUNE_FIRE.get());
                        output.accept(ModItems.RUNE_ICE.get());
                        output.accept(ModItems.RUNE_LIGHTNING.get());
                        output.accept(ModItems.GRIMOIRE_BOOK.get());
                    })
                    .build());
}
