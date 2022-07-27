package net.quantumgoddess.oxygencraft.damage;

import net.minecraft.entity.damage.DamageSource;

public class OCDamageSource extends DamageSource{

    public static final DamageSource CHOKE = new OCDamageSource("choke").setBypassesArmor();

    public OCDamageSource(String name) {
        super(name);
    }
    
}