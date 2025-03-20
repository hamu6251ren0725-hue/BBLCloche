package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.item.ClocheItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ClocheItemModelProvider extends ItemModelProvider {
    public ClocheItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cloche.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        //Misc Items
        simpleItem(ClocheItems.OVERWORLD_UPGRADE);
        simpleItem(ClocheItems.NETHER_UPGRADE);
        simpleItem(ClocheItems.END_UPGRADE);
        simpleItem(ClocheItems.NO_SEEDS_UPGRADE);
        simpleItem(ClocheItems.NO_OTHER_DROPS_UPGRADE);
        simpleItem(ClocheItems.MAIN_OUTPUT_UPGRADE);
        simpleItem(ClocheItems.SHEARS_UPGRADE);

        //Speed Upgrades
        simpleItem(ClocheItems.FIXED_SPEED_UPGRADE_1);
        simpleItem(ClocheItems.FIXED_SPEED_UPGRADE_2);
        simpleItem(ClocheItems.FIXED_SPEED_UPGRADE_3);
        simpleItem(ClocheItems.PERCENTAGE_SPEED_UPGRADE_1);
        simpleItem(ClocheItems.PERCENTAGE_SPEED_UPGRADE_2);
        simpleItem(ClocheItems.PERCENTAGE_SPEED_UPGRADE_3);
    }



    private void simpleItem(DeferredItem<Item> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "item/" + item.getId().getPath()));
    }

    @Override
    public String getName() {
        return Cloche.MOD_ID + " Item Models";
    }
}
