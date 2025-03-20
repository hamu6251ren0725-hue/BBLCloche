package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.cloche.util.ClocheTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ClocheItemTags extends ItemTagsProvider {

    ClocheItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags.contentsGetter(), Cloche.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        //Upgrades
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.NO_SEEDS_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.NO_OTHER_DROPS_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.END_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.NETHER_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.OVERWORLD_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.SHEARS_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.MAIN_OUTPUT_UPGRADE.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.PERCENTAGE_SPEED_UPGRADE_1.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.PERCENTAGE_SPEED_UPGRADE_2.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.PERCENTAGE_SPEED_UPGRADE_3.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.FIXED_SPEED_UPGRADE_1.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.FIXED_SPEED_UPGRADE_2.get());
        tag(ClocheTags.Items.UPGRADES).add(ClocheItems.FIXED_SPEED_UPGRADE_3.get());


    }


    @Override
    public @NotNull String getName() {
        return Cloche.MOD_ID + " Item Tags";
    }
}
