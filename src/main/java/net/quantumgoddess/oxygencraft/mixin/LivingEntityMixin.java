package net.quantumgoddess.oxygencraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Mixin(LivingEntity.class)
    public interface LivingEntityAccessor{
        @Accessor
        Random getRandom();
    }

    @Inject(at = @At("HEAD"), method = "baseTick()V")
    public void baseTick(CallbackInfo info){
        LivingEntity entity = ((LivingEntity)(Object)this);
        //copied drowning code and changed to work in air
        if (entity.isAlive()) {
            boolean bl = entity instanceof PlayerEntity;
            //check if player is in air
            if (entity.world.getBlockState(new BlockPos(entity.getX(), entity.getEyeY(), entity.getZ())).isOf(Blocks.AIR)) {
                //check if entity is a player or is invulnerable
                boolean bl2 = (!bl || !((PlayerEntity)entity).getAbilities().invulnerable);
                if (bl2) {
                    entity.setAir(getNextAir(entity, entity.getAir()));
                    if (entity.getAir() == -20) {
                        entity.setAir(0);
                        //TODO: set damage source?
                        entity.damage(DamageSource.DROWN, 2.0f);
                    }
                }
            }
        }
    }

    //calculate amount of air left on next tick
    protected int getNextAir(LivingEntity ent, int air) {
        int i = EnchantmentHelper.getRespiration(ent);
        if (i > 0 && ((LivingEntityAccessor) ent).getRandom().nextInt(i + 1) > 0) {
            return air;
        }
        //every tick out of water you gain 4 air, so -5 gives a nett of -1 air per tick
        return air - 5;
    }

}
