package net.grimoiremod;

import net.grimoiremod.item.ModCreativeTabs;
import net.grimoiremod.item.ModItems;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Grimoire: Path of the Spellcaster
 * <p>
 * A magic mod built around runes that can be combined to create spells.
 * This is a starter scaffold: three basic runes (Fire, Ice, Lightning)
 * and a Grimoire book item are already registered and functional.
 * <p>
 * Next steps you can build on top of this:
 * - Add a mana resource (capability) so runes cost mana to use.
 * - Add a custom GUI screen for the Grimoire to combine runes into spells.
 * - Add a Staff item that casts whatever spell is stored in the Grimoire.
 */
@Mod(GrimoireMod.MODID)
public class GrimoireMod {

    public static final String MODID = "grimoire";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public GrimoireMod(FMLJavaModLoadingContext context) {
        // Forge 61.x uses EventBus 7: the mod event bus is now a BusGroup,
        // obtained from the context instead of FMLJavaModLoadingContext.get().getModEventBus().
        BusGroup modBusGroup = context.getModBusGroup();

        ModItems.ITEMS.register(modBusGroup);
        ModCreativeTabs.TABS.register(modBusGroup);

        LOGGER.info("Grimoire mod initializing - runes and spells loading");
    }
}
