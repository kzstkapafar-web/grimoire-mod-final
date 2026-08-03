package net.grimoiremod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

/**
 * The Grimoire book. Right now it's a lore item with a tooltip.
 * This is the natural place to hook up a custom GUI (Screen + Menu)
 * that lets the player insert runes into slots to build spells.
 */
public class GrimoireItem extends Item {

    public GrimoireItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
                                 Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.literal("A book of forgotten spells.").withStyle(ChatFormatting.GRAY));
        tooltipAdder.accept(Component.literal("Combine runes to discover new magic.").withStyle(ChatFormatting.DARK_PURPLE));
    }
}
