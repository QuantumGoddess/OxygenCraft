package net.quantumgoddess.oxygencraft.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.quantumgoddess.oxygencraft.OCMain;

public class OCItems {

    public static final Item SPACESUIT_HELMET = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(OCMain.ITEM_GROUP));
    public static final Item SPACESUIT_CHESTPLATE = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(OCMain.ITEM_GROUP));
    public static final Item SPACESUIT_LEGGINGS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Settings().group(OCMain.ITEM_GROUP));
    public static final Item SPACESUIT_BOOTS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Settings().group(OCMain.ITEM_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("oxygencraft", "spacesuit_helmet"), SPACESUIT_HELMET);
        Registry.register(Registry.ITEM, new Identifier("oxygencraft", "spacesuit_chestplate"), SPACESUIT_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("oxygencraft", "spacesuit_leggings"), SPACESUIT_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("oxygencraft", "spacesuit_boots"), SPACESUIT_BOOTS);
    }

}