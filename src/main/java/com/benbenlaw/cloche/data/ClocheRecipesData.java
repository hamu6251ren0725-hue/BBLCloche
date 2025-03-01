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
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
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

        // Cloche Chorus Fruit
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(Items.CHORUS_FRUIT), Ingredient.of(Items.END_STONE), null,
                        "minecraft:end", 1200, ResultLists.CHORUS_FRUIT_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/chorus_fruit"));

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

        // Single Item Recipes
        createSingleItemRecipe(new ItemStack(Items.DANDELION), Ingredient.of(ItemTags.DIRT), 1200, "dandelion", consumer);
        createSingleItemRecipe(new ItemStack(Items.POPPY), Ingredient.of(ItemTags.DIRT), 1200, "poppy", consumer);
        createSingleItemRecipe(new ItemStack(Items.BLUE_ORCHID), Ingredient.of(ItemTags.DIRT), 1200, "blue_orchid", consumer);
        createSingleItemRecipe(new ItemStack(Items.ALLIUM), Ingredient.of(ItemTags.DIRT), 1200, "allium", consumer);
        createSingleItemRecipe(new ItemStack(Items.AZURE_BLUET), Ingredient.of(ItemTags.DIRT), 1200, "azure_bluet", consumer);
        createSingleItemRecipe(new ItemStack(Items.RED_TULIP), Ingredient.of(ItemTags.DIRT), 1200, "red_tulip", consumer);
        createSingleItemRecipe(new ItemStack(Items.ORANGE_TULIP), Ingredient.of(ItemTags.DIRT), 1200, "orange_tulip", consumer);
        createSingleItemRecipe(new ItemStack(Items.WHITE_TULIP), Ingredient.of(ItemTags.DIRT), 1200, "white_tulip", consumer);
        createSingleItemRecipe(new ItemStack(Items.PINK_TULIP), Ingredient.of(ItemTags.DIRT), 1200, "pink_tulip", consumer);
        createSingleItemRecipe(new ItemStack(Items.OXEYE_DAISY), Ingredient.of(ItemTags.DIRT), 1200, "oxeye_daisy", consumer);
        createSingleItemRecipe(new ItemStack(Items.CORNFLOWER), Ingredient.of(ItemTags.DIRT), 1200, "cornflower", consumer);
        createSingleItemRecipe(new ItemStack(Items.LILY_OF_THE_VALLEY), Ingredient.of(ItemTags.DIRT), 1200, "lily_of_the_valley", consumer);
        createSingleItemRecipe(new ItemStack(Items.WITHER_ROSE), Ingredient.of(ItemTags.DIRT), 1200, "wither_rose", consumer);
        createSingleItemRecipe(new ItemStack(Items.PINK_PETALS), Ingredient.of(ItemTags.DIRT), 1200, "pink_petals", consumer);
        createSingleItemRecipe(new ItemStack(Items.SPORE_BLOSSOM), Ingredient.of(ItemTags.DIRT), 1200, "spore_blossom", consumer);
        createSingleItemRecipe(new ItemStack(Items.FERN), Ingredient.of(ItemTags.DIRT), 1200, "fern", consumer);
        createSingleItemRecipe(new ItemStack(Items.SHORT_GRASS), Ingredient.of(ItemTags.DIRT), 1200, "short_grass", consumer);
        createSingleItemRecipe(new ItemStack(Items.SUNFLOWER), Ingredient.of(ItemTags.DIRT), 1200, "sunflower", consumer);
        createSingleItemRecipe(new ItemStack(Items.LILAC), Ingredient.of(ItemTags.DIRT), 1200, "lilac", consumer);
        createSingleItemRecipe(new ItemStack(Items.ROSE_BUSH), Ingredient.of(ItemTags.DIRT), 1200, "rose_bush", consumer);
        createSingleItemRecipe(new ItemStack(Items.PEONY), Ingredient.of(ItemTags.DIRT), 1200, "peony", consumer);
        createSingleItemRecipe(new ItemStack(Items.GLOW_LICHEN), Ingredient.of(Tags.Items.STONES), 1200, "glow_lichen", consumer);
        createSingleItemRecipe(new ItemStack(Items.LILY_PAD), Ingredient.of(Tags.Items.BUCKETS_WATER), 1200, "lily_pad", consumer);
        createSingleItemRecipe(new ItemStack(Items.SEAGRASS), Ingredient.of(Tags.Items.BUCKETS_WATER), 1200, "seagrass", consumer);
        createSingleItemRecipe(new ItemStack(Items.SEA_PICKLE), Ingredient.of(Tags.Items.BUCKETS_WATER), 1200, "sea_pickle", consumer);
        createSingleItemRecipe(new ItemStack(Items.KELP), Ingredient.of(Tags.Items.BUCKETS_WATER), 1200, "kelp", consumer);
        createSingleItemRecipe(new ItemStack(Items.VINE), Ingredient.of(ItemTags.JUNGLE_LOGS), 1200, "vine", consumer);
        createSingleItemRecipe(new ItemStack(Items.COCOA_BEANS), Ingredient.of(ItemTags.JUNGLE_LOGS), 1200, "cocoa_beans", consumer);
        createSingleItemRecipe(new ItemStack(Items.BAMBOO), Ingredient.of(ItemTags.DIRT), 1200, "bamboo", consumer);
        createSingleItemRecipe(new ItemStack(Items.SUGAR_CANE), Ingredient.of(ItemTags.DIRT), 1200, "sugar_cane", consumer);
        createSingleItemRecipe(new ItemStack(Items.CACTUS), Ingredient.of(ItemTags.SAND), 1200, "cactus", consumer);
        createSingleItemRecipe(new ItemStack(Items.SWEET_BERRIES), Ingredient.of(ItemTags.DIRT), 1200, "sweet_berries", consumer);
        createSingleItemRecipe(new ItemStack(Items.NETHER_WART), Ingredient.of(Items.SOUL_SAND), 1200, "nether_wart", consumer);
        createSingleItemRecipe(new ItemStack(Items.CARROT), Ingredient.of(ItemTags.DIRT), 1200, "carrot", consumer);
        createSingleItemRecipe(new ItemStack(Items.BROWN_MUSHROOM), Ingredient.of(ItemTags.DIRT), 1200, "brown_mushroom", consumer);
        createSingleItemRecipe(new ItemStack(Items.RED_MUSHROOM), Ingredient.of(ItemTags.DIRT), 1200, "red_mushroom", consumer);
            }


    public void createSingleItemRecipe(ItemStack item, Ingredient soil, int duration, String name, RecipeOutput consumer) {

        NonNullList<ChanceResult> SINGLE_ITEM_RESULTS = NonNullList.create();
        SINGLE_ITEM_RESULTS.add(new ChanceResult(new ItemStack(item.getItem()), 1.0f));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(item), soil, null,
                        null, duration, SINGLE_ITEM_RESULTS, null)
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/" + name));

    }
}
