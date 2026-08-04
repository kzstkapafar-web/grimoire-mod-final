package net.grimoiremod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GrimoireItem extends Item {
    public GrimoireItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
                                 TooltipFlag flag) {
        tooltipComponents.add(Component.literal("A book of forgotten spells.").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.literal("Combine runes to discover new magic.").withStyle(ChatFormatting.DARK_PURPLE));
    }
}
