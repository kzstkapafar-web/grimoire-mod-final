package net.grimoiremod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

/**
 * A temporary undead servant summoned by the Curse Rune.
 * It's given an initial target right after spawning (see RuneItem.castCurse),
 * wanders and retaliates if struck, and despawns automatically after its
 * lifespan runs out.
 */
public class WraithEntity extends Monster {

    private int lifespan = 200; // ~10 seconds at 20 ticks/sec

    public WraithEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.ARMOR, 2.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    /** Called right after spawning to give the wraith an initial victim. */
    public void setInitialTarget(LivingEntity target) {
        this.setTarget(target);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide() && lifespan > 0) {
            lifespan--;
            if (lifespan <= 0) {
                this.discard();
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceSquared) {
        // Lifespan controls removal, not vanilla far-away despawning.
        return false;
    }
}
