package net.dachi.dream.item;

import net.dachi.dream.Dream;
import net.dachi.dream.armormaterial.ModArmorMaterial;
import net.dachi.dream.creativetabs.ModItemGroup;
import net.dachi.dream.entity.ModEntityTypes;
import net.dachi.dream.fluid.ModFluids;
import net.dachi.dream.item.custom.DreamLighter;
import net.dachi.dream.item.custom.DreamSwordItem;
import net.dachi.dream.itemtier.ModItemTier;
import net.dachi.dream.util.ModSoundEvents;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Dream.MOD_ID);


    public static final RegistryObject<Item> DREAM_INGOT = ITEMS.register("dream_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAM_LIGHTER = ITEMS.register("dream_lighter",
            ()-> new DreamLighter(new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));


    public static final RegistryObject<Item> DREAM_SWORD = ITEMS.register("dream_sword",
            () -> new DreamSwordItem(ModItemTier.DREAM, 2, 3f,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));


    public static final RegistryObject<Item> DREAM_PICKAXE = ITEMS.register("dream_pickaxe",
            () -> new PickaxeItem(ModItemTier.DREAM, 0, -1f,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAM_SHOVEL = ITEMS.register("dream_shovel",
            () -> new ShovelItem(ModItemTier.DREAM, 0, -1f,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAM_AXE = ITEMS.register("dream_axe",
            () -> new AxeItem(ModItemTier.DREAM, 4, -6f,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAM_HOE = ITEMS.register("dream_hoe",
            () -> new HoeItem(ModItemTier.DREAM, 0, 0f,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));


    public static final RegistryObject<Item> DREAMING_BOOTS = ITEMS.register("dreaming_boots",
            () -> new ArmorItem(ModArmorMaterial.DREAM, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAMING_CHESTPLATE = ITEMS.register("dreaming_chestplate",
            () -> new ArmorItem(ModArmorMaterial.DREAM, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAMING_LEGGINGS = ITEMS.register("dreaming_leggings",
            () -> new ArmorItem(ModArmorMaterial.DREAM, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> DREAMING_HELMET = ITEMS.register("dreaming_helmet",
            () -> new ArmorItem(ModArmorMaterial.DREAM, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> AMONG_US_DRIP_MUSIC_DISC = ITEMS.register("among_us_drip_music_disc",
            () -> new MusicDiscItem(1, () -> ModSoundEvents.AMONG_US_DRIP.get(),
                    new Item.Properties().maxStackSize(1).group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(),
                    new Item.Properties().maxStackSize(1).group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.PENGUIN,0x948e8d, 0x3b3635,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> NIGHTMARE_BLAZE_SPAWN_EGG = ITEMS.register("nightmare_blaze_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.NIGHTMARE_BLAZE,000000, 808080,
                    new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
