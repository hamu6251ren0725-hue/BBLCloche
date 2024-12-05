package com.benbenlaw.cloche.data.recipe;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
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

public class DimensionalUpgradeRecipeProvider implements RecipeBuilder {

    protected String group;
    protected Ingredient ingredient;
    protected String dimension;

    protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public DimensionalUpgradeRecipeProvider(Ingredient ingredient, String dimension) {
        this.ingredient = ingredient;
        this.dimension = dimension != null ? dimension : "all";

    }

    public static DimensionalUpgradeRecipeProvider DimensionalUpgradeRecipeBuilder(Ingredient ingredient, String dimension) {
        return new DimensionalUpgradeRecipeProvider(ingredient, dimension);
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
        return ItemStack.EMPTY.getItem();
    }

    public void save(@NotNull RecipeOutput recipeOutput) {
        this.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "upgrades/")
        );
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        DimensionalUpgradeRecipe clocheRecipe = new DimensionalUpgradeRecipe(ingredient, dimension);
        recipeOutput.accept(id, clocheRecipe, builder.build(id.withPrefix("recipes/upgrades/")));

    }



}
