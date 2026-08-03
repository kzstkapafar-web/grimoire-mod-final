package net.grimoiremod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * A rune item. Each rune type produces a different spell effect when used
 * (right click). This is intentionally simple so it's easy to follow and
 * extend - e.g. add mana cost, cooldowns, or combine runes for new spells.
 */
public class RuneItem extends Item {

    public enum RuneType {
        FIRE,
        ICE,
        LIGHTNING
    }

    private final RuneType type;

    public RuneItem(Properties properties, RuneType type) {
        super(properties);
        this.type = type;
    }

    public RuneType getRuneType() {
        return type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            switch (type) {
                case FIRE -> castFire(level, player);
                case ICE -> castIce(level, player);
                case LIGHTNING -> castLightning(level, player);
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            level.playSound(null, player.blockPosition(), SoundEvents.EVOKER_CAST_SPELL,
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

private void castFire(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        SmallFireball fireball = new SmallFireball(level, player, look);
        fireball.setPos(player.getX() + look.x, player.getEyeY(), player.getZ() + look.z);
        level.addFreshEntity(fireball);
    }

    private void castIce(Level level, Player player) {
        for (LivingEntity target : level.getEntitiesOfClass(
                LivingEntity.class, player.getBoundingBox().inflate(5.0), e -> e != player)) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 1));
        }
    }

    private void castLightning(Level level, Player player) {
        LivingEntity target = level.getEntitiesOfClass(
                        LivingEntity.class, player.getBoundingBox().inflate(8.0), e -> e != player)
                .stream().findFirst().orElse(null);

        Vec3 pos = target != null
                ? target.position()
                : player.position().add(player.getLookAngle().scale(5));

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.x, pos.y, pos.z);
            level.addFreshEntity(lightning);
        }
    }
}
