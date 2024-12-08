package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.data.recipe.ClocheRecipeProvider;
import com.benbenlaw.cloche.data.recipe.DimensionalUpgradeRecipeProvider;
import com.benbenlaw.cloche.data.recipe.ResultLists;
import com.benbenlaw.cloche.data.recipe.SpeedRecipeProvider;
import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.cloche.recipe.SpeedUpgradeRecipe;
import com.benbenlaw.core.item.CoreItems;
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

        // Fixed Speed Upgrade 1 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.FIXED_SPEED_UPGRADE_1.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('G', Tags.Items.INGOTS_GOLD)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Fixed Speed Upgrade 2 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.FIXED_SPEED_UPGRADE_2.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', ClocheItems.FIXED_SPEED_UPGRADE_1.get())
                .define('G', Tags.Items.GEMS_DIAMOND)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Fixed Speed Upgrade 3 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.FIXED_SPEED_UPGRADE_3.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', ClocheItems.FIXED_SPEED_UPGRADE_2.get())
                .define('G', Tags.Items.OBSIDIANS)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Percentage Speed Upgrade 1 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.PERCENTAGE_SPEED_UPGRADE_1.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('G', Items.CLOCK)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Percentage Speed Upgrade 2 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.PERCENTAGE_SPEED_UPGRADE_2.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', ClocheItems.PERCENTAGE_SPEED_UPGRADE_1.get())
                .define('G', Tags.Items.GEMS_DIAMOND)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Percentage Speed Upgrade 3 - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.PERCENTAGE_SPEED_UPGRADE_3.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', ClocheItems.PERCENTAGE_SPEED_UPGRADE_2.get())
                .define('G', Tags.Items.OBSIDIANS)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Shears Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.SHEARS_UPGRADE.get())
                .pattern(" S ")
                .pattern("SUS")
                .pattern(" S ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('S', Tags.Items.TOOLS_SHEAR)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Main Output Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.MAIN_OUTPUT_UPGRADE.get())
                .pattern(" C ")
                .pattern("CUC")
                .pattern(" C ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('C', Items.GOLDEN_CARROT)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // End Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.END_UPGRADE.get())
                .pattern(" P ")
                .pattern("PUP")
                .pattern(" P ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('P', Items.PURPUR_BLOCK)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Nether Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.NETHER_UPGRADE.get())
                .pattern(" W ")
                .pattern("WUW")
                .pattern(" W ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('W', Items.NETHER_WART_BLOCK)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Overworld Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.OVERWORLD_UPGRADE.get())
                .pattern(" G ")
                .pattern("GUG")
                .pattern(" G ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('G', Items.GRASS_BLOCK)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // No Seed Upgrade - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheItems.NO_SEEDS_UPGRADE.get())
                .pattern(" S ")
                .pattern("SUS")
                .pattern(" S ")
                .define('U', CoreItems.UPGRADE_BASE)
                .define('S', Tags.Items.SEEDS)
                .group("cloche")
                .unlockedBy("has_item", has(CoreItems.UPGRADE_BASE))
                .save(consumer);

        // Cloche - Crafting Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ClocheBlocks.CLOCHE.get())
                .pattern("III")
                .pattern("WDW")
                .pattern("III")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('W', Tags.Items.BUCKETS_WATER)
                .define('D',  ItemTags.DIRT)
                .group("cloche")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
                .save(consumer);

        // Upgrades - Speed
        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.FIXED_SPEED_UPGRADE_1), "fixed", 100)
                .unlockedBy("has_item", has(ClocheItems.FIXED_SPEED_UPGRADE_1))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/fixed_speed_1"));
        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.FIXED_SPEED_UPGRADE_2), "fixed", 200)
                .unlockedBy("has_item", has(ClocheItems.FIXED_SPEED_UPGRADE_2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/fixed_speed_2"));
        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.FIXED_SPEED_UPGRADE_3), "fixed", 300)
                .unlockedBy("has_item", has(ClocheItems.FIXED_SPEED_UPGRADE_3))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/fixed_speed_3"));

        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.PERCENTAGE_SPEED_UPGRADE_1), "percentage", 5)
                .unlockedBy("has_item", has(ClocheItems.PERCENTAGE_SPEED_UPGRADE_1))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/percentage_speed_1"));
        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.PERCENTAGE_SPEED_UPGRADE_2), "percentage", 10)
                .unlockedBy("has_item", has(ClocheItems.PERCENTAGE_SPEED_UPGRADE_2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/percentage_speed_2"));
        SpeedRecipeProvider.SpeedUpgradeRecipeBuilder(Ingredient.of(ClocheItems.PERCENTAGE_SPEED_UPGRADE_3), "percentage", 15)
                .unlockedBy("has_item", has(ClocheItems.PERCENTAGE_SPEED_UPGRADE_3))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/percentage_speed_3"));

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
                        null, 1200, ResultLists.WHEAT_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/wheat"));

        // Cloche Potato
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.POTATO), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.POTATO_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/potato"));

        // Cloche Carrot
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CARROT), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.CARROT_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/carrot"));

        // Cloche Beetroot
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BEETROOT_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BEETROOT_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/beetroot"));

        // Cloche Melon
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.MELON_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.MELON_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/melon"));

        // Cloche Pumpkin
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.PUMPKIN_SEEDS), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.PUMPKIN_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/pumpkin"));

        // Cloche Sugar Cane
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SUGAR_CANE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SUGAR_CANE_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/sugar_cane"));

        // Cloche Cactus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CACTUS), Ingredient.of(ItemTags.SAND), null,
                        null, 1200, ResultLists.CACTUS_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cactus"));

        // Cloche Sweet Berry
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SWEET_BERRY_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/sweet_berry"));

        // Cloche Nether Wart
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SOUL_SAND), null,
                        "minecraft:the_nether", 1200, ResultLists.NETHER_WART_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/nether_wart"));

        // Cloche Cocoa Beans
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.COCOA_BEANS), Ingredient.of(ItemTags.JUNGLE_LOGS), null,
                        null, 1200, ResultLists.COCOA_BEANS_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cocoa_beans"));

        // Cloche Chorus Fruit
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CHORUS_FRUIT), Ingredient.of(Items.END_STONE), null,
                        "minecraft:end", 1200, ResultLists.CHORUS_FRUIT_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/chorus_fruit"));

        // Cloche Bamboo
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BAMBOO), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BAMBOO_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/bamboo"));

        // Cloche Kelp
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.KELP), Ingredient.of(Tags.Items.BUCKETS_WATER), null,
                        null, 1200, ResultLists.KELP_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/kelp"));

        // Cloche Brown Mushroom
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BROWN_MUSHROOM), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BROWN_MUSHROOM_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/brown_mushroom"));

        // Cloche Red Mushroom
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.RED_MUSHROOM), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.RED_MUSHROOM_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/red_mushroom"));

        // Cloche Lily Pad
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.LILY_PAD), Ingredient.of(Tags.Items.BUCKETS_WATER), null,
                        null, 1200, ResultLists.LILY_PAD_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/lily_pad"));

        // Cloche Vine
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.VINE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.VINE_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/vine"));

        // Oak Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.OAK_SAPLING_RESULTS, new ItemStack(Items.OAK_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/oak_sapling"));

        // Spruce Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.SPRUCE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.SPRUCE_SAPLING_RESULTS, new ItemStack(Items.SPRUCE_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/spruce_sapling"));

        // Birch Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.BIRCH_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.BIRCH_SAPLING_RESULTS, new ItemStack(Items.BIRCH_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/birch_sapling"));

        // Jungle Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.JUNGLE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.JUNGLE_SAPLING_RESULTS, new ItemStack(Items.JUNGLE_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/jungle_sapling"));

        // Acacia Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.ACACIA_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.ACACIA_SAPLING_RESULTS, new ItemStack(Items.ACACIA_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/acacia_sapling"));

        // Cherry Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CHERRY_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.CHERRY_SAPLING_RESULTS, new ItemStack(Items.CHERRY_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/cherry_sapling"));

        //Mangrove Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.MANGROVE_PROPAGULE), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.MANGROVE_SAPLING_RESULTS, new ItemStack(Items.MANGROVE_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/mangrove_sapling"));

        // Dark Oak Sapling
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.DARK_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, ResultLists.DARK_OAK_SAPLING_RESULTS, new ItemStack(Items.DARK_OAK_LEAVES, 2))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/dark_oak_sapling"));

        // Crimson Fungus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CRIMSON_FUNGUS), Ingredient.of(ItemTags.DIRT), null,
                        "minecraft:the_nether", 1200, ResultLists.CRIMSON_FUNGUS_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/crimson_fungus"));

        // Warped Fungus
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.WARPED_FUNGUS), Ingredient.of(ItemTags.DIRT), null,
                        "minecraft:the_nether", 1200, ResultLists.WARPED_FUNGUS_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/warped_fungus"));




























    }

}
