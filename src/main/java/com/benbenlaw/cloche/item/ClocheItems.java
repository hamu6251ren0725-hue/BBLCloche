package com.benbenlaw.cloche.item;

import com.benbenlaw.cloche.Cloche;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ClocheItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cloche.MOD_ID);

    public static final DeferredItem<Item> OVERWORLD_UPGRADE = ITEMS .register("overworld_upgrade",
            () -> new UpgradeItem(new Item.Properties().stacksTo(1), "tooltip.cloche.upgrade.overworld"));
    public static final DeferredItem<Item> NETHER_UPGRADE = ITEMS.register("nether_upgrade",
            () -> new UpgradeItem(new Item.Properties().stacksTo(1), "tooltip.cloche.upgrade.nether"));
    public static final DeferredItem<Item> END_UPGRADE = ITEMS.register("end_upgrade",
            () -> new UpgradeItem(new Item.Properties().stacksTo(1), "tooltip.cloche.upgrade.end"));
    public static final DeferredItem<Item> NO_SEEDS_UPGRADE = ITEMS.register("no_seeds_upgrade",
            () -> new UpgradeItem(new Item.Properties().stacksTo(1), "tooltip.cloche.upgrade.no_seeds"));
    public static final DeferredItem<Item> MAIN_OUTPUT_UPGRADE = ITEMS.register("main_output_upgrade",
            () -> new UpgradeItem(new Item.Properties().stacksTo(1), "tooltip.cloche.upgrade.main_output_upgrade"));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
