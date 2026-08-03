package net.grimoiremod.item;

import net.grimoiremod.GrimoireMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GrimoireMod.MODID);

    public static final RegistryObject<Item> RUNE_FIRE = ITEMS.register("rune_fire",
            () -> new RuneItem(new Item.Properties().stacksTo(64), RuneItem.RuneType.FIRE));
    public static final RegistryObject<Item> RUNE_ICE = ITEMS.register("rune_ice",
            () -> new RuneItem(new Item.Properties().stacksTo(64), RuneItem.RuneType.ICE));
    public static final RegistryObject<Item> RUNE_LIGHTNING = ITEMS.register("rune_lightning",
            () -> new RuneItem(new Item.Properties().stacksTo(64), RuneItem.RuneType.LIGHTNING));
    public static final RegistryObject<Item> RUNE_CURSE = ITEMS.register("rune_curse",
            () -> new RuneItem(new Item.Properties().stacksTo(64), RuneItem.RuneType.CURSE));
    public static final RegistryObject<Item> GRIMOIRE_BOOK = ITEMS.register("grimoire_book",
            () -> new GrimoireItem(new Item.Properties().stacksTo(1)));
}
