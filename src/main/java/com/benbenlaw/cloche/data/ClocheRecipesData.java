package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.data.recipe.ClocheRecipeProvider;
import com.benbenlaw.cloche.data.recipe.DimensionalUpgradeRecipeProvider;
import com.benbenlaw.cloche.data.recipe.ResultLists;
import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.core.recipe.ChanceResult;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ClocheRecipesData extends RecipeProvider {

    public ClocheRecipesData(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        // Cloche
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheBlocks.CLOCHE.get())
                .pattern("III")
                .pattern("WDW")
                .pattern("III")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('D',  ItemTags.DIRT)
                .group("opolisutilities")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
                .save(consumer);

        // Upgrades - Dimensional

        DimensionalUpgradeRecipeProvider.DimensionalUpgradeRecipeBuilder((Ingredient.of(ClocheItems.OVERWORLD_UPGRADE)), "minecraft:overworld")
                .unlockedBy("has_item", has(ClocheItems.OVERWORLD_UPGRADE))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/overworld"));

        DimensionalUpgradeRecipeProvider.DimensionalUpgradeRecipeBuilder((Ingredient.of(ClocheItems.NETHER_UPGRADE)), "minecraft:the_nether")
                .unlockedBy("has_item", has(ClocheItems.NETHER_UPGRADE))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/nether"));

        DimensionalUpgradeRecipeProvider.DimensionalUpgradeRecipeBuilder((Ingredient.of(ClocheItems.END_UPGRADE)), "minecraft:end")
                .unlockedBy("has_item", has(ClocheItems.END_UPGRADE))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/end"));

        // Cloche Wheat
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.WHEAT_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.WHEAT_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/wheat"));

        // Cloche Potato
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.POTATO), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.POTATO_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/potato"));

        // Cloche Carrot
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CARROT), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.CARROT_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/carrot"));

        // Cloche Beetroot
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BEETROOT_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BEETROOT_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/beetroot"));

        // Cloche Melon
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.MELON_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.MELON_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/melon"));

        // Cloche Pumpkin
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.PUMPKIN_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.PUMPKIN_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/pumpkin"));

        // Cloche Sugar Cane
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SUGAR_CANE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SUGAR_CANE_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/sugar_cane"));

        // Cloche Cactus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CACTUS), Ingredient.of(ItemTags.SAND), null,
                        null, 1200, ResultLists.CACTUS_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cactus"));

        // Cloche Sweet Berry
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SWEET_BERRY_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/sweet_berry"));

        // Cloche Nether Wart
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SOUL_SAND), null,
                        "minecraft:the_nether", 1200, ResultLists.NETHER_WART_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/nether_wart"));

        // Cloche Cocoa Beans
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.COCOA_BEANS), Ingredient.of(ItemTags.JUNGLE_LOGS), null,
                        null, 1200, ResultLists.COCOA_BEANS_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cocoa_beans"));

        // Cloche Chorus Fruit
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CHORUS_FRUIT), Ingredient.of(Items.END_STONE), null,
                        "minecraft:end", 1200, ResultLists.CHORUS_FRUIT_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/chorus_fruit"));

        // Cloche Bamboo
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BAMBOO), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BAMBOO_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/bamboo"));

        // Cloche Kelp
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.KELP), Ingredient.of(Tags.Items.BUCKETS_WATER), null,
                        null, 1200, ResultLists.KELP_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/kelp"));

        // Cloche Brown Mushroom
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BROWN_MUSHROOM), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BROWN_MUSHROOM_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/brown_mushroom"));

        // Cloche Red Mushroom
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.RED_MUSHROOM), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.RED_MUSHROOM_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/red_mushroom"));

        // Cloche Lily Pad
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.LILY_PAD), Ingredient.of(Tags.Items.BUCKETS_WATER), null,
                        null, 1200, ResultLists.LILY_PAD_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/lily_pad"));

        // Cloche Vine
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.VINE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.VINE_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/vine"));

        // Oak Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.OAK_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/oak_sapling"));

        // Spruce Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SPRUCE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SPRUCE_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/spruce_sapling"));

        // Birch Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BIRCH_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BIRCH_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/birch_sapling"));

        // Jungle Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.JUNGLE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.JUNGLE_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/jungle_sapling"));

        // Acacia Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.ACACIA_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.ACACIA_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/acacia_sapling"));

        // Cherry Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CHERRY_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.CHERRY_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cherry_sapling"));

        //Mangrove Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.MANGROVE_PROPAGULE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.MANGROVE_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/mangrove_sapling"));

        // Dark Oak Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.DARK_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.DARK_OAK_SAPLING_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/dark_oak_sapling"));

        // Crimson Fungus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CRIMSON_FUNGUS), Ingredient.of(ItemTags.DIRT), null,
                        "minecraft:the_nether", 1200, ResultLists.CRIMSON_FUNGUS_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/crimson_fungus"));

        // Warped Fungus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.WARPED_FUNGUS), Ingredient.of(ItemTags.DIRT), null,
                        "minecraft:the_nether", 1200, ResultLists.WARPED_FUNGUS_RESULTS)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/warped_fungus"));




























    }

}
