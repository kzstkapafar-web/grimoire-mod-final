package net.grimoiremod;

import net.grimoiremod.entity.ModEntityTypes;
import net.grimoiremod.entity.WraithEntity;
import net.grimoiremod.item.ModCreativeTabs;
import net.grimoiremod.item.ModItems;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Grimoire: Path of the Spellcaster
 * <p>
 * A magic mod built around runes that can be combined to create spells.
 * Fire, Ice, Lightning and Curse runes are registered and functional,
 * plus a Grimoire book item.
 */
@Mod(GrimoireMod.MODID)
public class GrimoireMod {

    public static final String MODID = "grimoire";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public GrimoireMod(FMLJavaModLoadingContext context) {
        // Forge 61.x (MC 1.21.11) uses EventBus 7: the mod event bus is now a
        // BusGroup, obtained from the context instead of the old
        // FMLJavaModLoadingContext.get().getModEventBus().
        BusGroup modBusGroup = context.getModBusGroup();

        ModItems.ITEMS.register(modBusGroup);
        ModCreativeTabs.TABS.register(modBusGroup);
        ModEntityTypes.ENTITY_TYPES.register(modBusGroup);

        EntityAttributeCreationEvent.getBus(modBusGroup).addListener(this::registerAttributes);

        LOGGER.info("Grimoire mod initializing - runes and spells loading");
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.WRAITH.get(), WraithEntity.createAttributes().build());
    }
}
