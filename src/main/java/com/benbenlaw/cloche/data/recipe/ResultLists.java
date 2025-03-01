package com.benbenlaw.cloche.data.recipe;

import com.benbenlaw.core.recipe.ChanceResult;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ResultLists {

    public static final NonNullList<ChanceResult> WHEAT_RESULTS;
    public static final NonNullList<ChanceResult> POTATO_RESULTS;
    public static final NonNullList<ChanceResult> BEETROOT_RESULTS;
    public static final NonNullList<ChanceResult> MELON_RESULTS;
    public static final NonNullList<ChanceResult> PUMPKIN_RESULTS;
    public static final NonNullList<ChanceResult> CHORUS_FRUIT_RESULTS;
    public static final NonNullList<ChanceResult> CRIMSON_FUNGUS_RESULTS;
    public static final NonNullList<ChanceResult> WARPED_FUNGUS_RESULTS;
    public static final NonNullList<ChanceResult> OAK_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> SPRUCE_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> BIRCH_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> JUNGLE_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> ACACIA_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> DARK_OAK_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> CHERRY_SAPLING_RESULTS;
    public static final NonNullList<ChanceResult> MANGROVE_SAPLING_RESULTS;


    static {
        WHEAT_RESULTS = NonNullList.create();
        WHEAT_RESULTS.add(new ChanceResult(new ItemStack(Items.WHEAT), 1.0f));
        WHEAT_RESULTS.add(new ChanceResult(new ItemStack(Items.WHEAT_SEEDS), 0.2f));

        POTATO_RESULTS = NonNullList.create();
        POTATO_RESULTS.add(new ChanceResult(new ItemStack(Items.POTATO), 1.0f));
        POTATO_RESULTS.add(new ChanceResult(new ItemStack(Items.POISONOUS_POTATO), 0.1f));

        BEETROOT_RESULTS = NonNullList.create();
        BEETROOT_RESULTS.add(new ChanceResult(new ItemStack(Items.BEETROOT), 1.0f));
        BEETROOT_RESULTS.add(new ChanceResult(new ItemStack(Items.BEETROOT_SEEDS), 0.2f));

        MELON_RESULTS = NonNullList.create();
        MELON_RESULTS.add(new ChanceResult(new ItemStack(Items.MELON), 1.0f));

        PUMPKIN_RESULTS = NonNullList.create();
        PUMPKIN_RESULTS.add(new ChanceResult(new ItemStack(Items.PUMPKIN), 1.0f));

        CHORUS_FRUIT_RESULTS = NonNullList.create();
        CHORUS_FRUIT_RESULTS.add(new ChanceResult(new ItemStack(Items.CHORUS_FRUIT), 1.0f));

        CRIMSON_FUNGUS_RESULTS = NonNullList.create();
        CRIMSON_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.CRIMSON_STEM, 2), 1.0f));
        CRIMSON_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.CRIMSON_FUNGUS), 0.5f));
        CRIMSON_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.SHROOMLIGHT), 0.1f));
        CRIMSON_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.NETHER_WART_BLOCK), 0.4f));

        WARPED_FUNGUS_RESULTS = NonNullList.create();
        WARPED_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.WARPED_STEM, 2), 1.0f));
        WARPED_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.WARPED_FUNGUS), 0.5f));
        WARPED_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.SHROOMLIGHT), 0.1f));
        WARPED_FUNGUS_RESULTS.add(new ChanceResult(new ItemStack(Items.WARPED_WART_BLOCK), 0.4f));

        OAK_SAPLING_RESULTS = NonNullList.create();
        OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.OAK_LOG, 2), 1.0f));
        OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.OAK_SAPLING), 0.2f));
        OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.APPLE), 0.2f));
        OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        SPRUCE_SAPLING_RESULTS = NonNullList.create();
        SPRUCE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.SPRUCE_LOG, 2), 1.0f));
        SPRUCE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.SPRUCE_SAPLING), 0.2f));
        SPRUCE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        BIRCH_SAPLING_RESULTS = NonNullList.create();
        BIRCH_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.BIRCH_LOG, 2), 1.0f));
        BIRCH_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.BIRCH_SAPLING), 0.2f));
        BIRCH_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        JUNGLE_SAPLING_RESULTS = NonNullList.create();
        JUNGLE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.JUNGLE_LOG, 2), 1.0f));
        JUNGLE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.JUNGLE_SAPLING), 0.2f));
        JUNGLE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.COCOA_BEANS), 0.2f));

        ACACIA_SAPLING_RESULTS = NonNullList.create();
        ACACIA_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.ACACIA_LOG, 2), 1.0f));
        ACACIA_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.ACACIA_SAPLING), 0.2f));
        ACACIA_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        DARK_OAK_SAPLING_RESULTS = NonNullList.create();
        DARK_OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.DARK_OAK_LOG, 2), 1.0f));
        DARK_OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.DARK_OAK_SAPLING), 0.2f));
        DARK_OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.APPLE), 0.2f));
        DARK_OAK_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        CHERRY_SAPLING_RESULTS = NonNullList.create();
        CHERRY_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.CHERRY_LOG, 2), 1.0f));
        CHERRY_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.CHERRY_SAPLING), 0.2f));
        CHERRY_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        MANGROVE_SAPLING_RESULTS = NonNullList.create();
        MANGROVE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.MANGROVE_LOG, 2), 1.0f));
        MANGROVE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.MANGROVE_PROPAGULE), 0.2f));
        MANGROVE_SAPLING_RESULTS.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));




    }
}