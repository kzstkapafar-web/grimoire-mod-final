package net.grimoiremod;

import net.grimoiremod.entity.ModEntityTypes;
import net.grimoiremod.item.ModCreativeTabs;
import net.grimoiremod.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(GrimoireMod.MODID)
public class GrimoireMod {
    public static final String MODID = "grimoire";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public GrimoireMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        LOGGER.info("Grimoire mod initializing - runes and spells loading");
    }
}
