package com.benbenlaw.cloche.data.recipe;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.core.recipe.ChanceResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClocheRecipeProvider implements RecipeBuilder {

    protected String group;
    protected Ingredient seed;
    protected Ingredient soil;
    protected Ingredient catalyst;
    protected String dimension;
    protected int duration;
    protected NonNullList<ChanceResult> results;
    protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    protected ItemStack shearsResult;

    public ClocheRecipeProvider(Ingredient seed, Ingredient soil, Ingredient catalyst, String dimension, int duration, NonNullList<ChanceResult> results, ItemStack shearsResult) {
        this.seed = seed;
        this.soil = soil;
        this.catalyst = catalyst != null ? catalyst : Ingredient.of(ItemStack.EMPTY);
        this.dimension = dimension != null ? dimension : "all";
        this.duration = duration;
        this.results = results;
        this.shearsResult = shearsResult != null ? shearsResult : ItemStack.EMPTY;
    }

    public static ClocheRecipeProvider ClocheRecipeBuilder(Ingredient seed, Ingredient soil, Ingredient catalyst, String dimension, int duration, NonNullList<ChanceResult> results, ItemStack shearsResult) {
        return new ClocheRecipeProvider(seed, soil, catalyst, dimension, duration, results, shearsResult);
    }
    @Override
    public @NotNull RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return results.getFirst().stack().getItem();
    }

    public void save(@NotNull RecipeOutput recipeOutput) {
        this.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche/")
        );
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        ClocheRecipe clocheRecipe = new ClocheRecipe(seed, soil, catalyst, dimension, duration, results, shearsResult);
        recipeOutput.accept(id, clocheRecipe, builder.build(id.withPrefix("recipes/cloche/")));

    }



}
