package com.benbenlaw.cloche.integration.jei;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.cloche.recipe.ClocheRecipes;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIClochePlugin implements IModPlugin {

    public static IDrawableStatic slotDrawable;
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "jei_plugin");
    }
    public static RecipeType<ClocheRecipe> CLOCHE =
            new RecipeType<>(ClocheRecipeCategory.UID, ClocheRecipe.class);

    public static RecipeType<ItemStack> CLOCHE_UPGRADES =
            RecipeType.create(Cloche.MOD_ID, "cloche_upgrades", ItemStack.class);

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ClocheBlocks.CLOCHE.get()), ClocheRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ClocheBlocks.CLOCHE.get()), CLOCHE_UPGRADES);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new
                ClocheRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new
                ClocheUpgradesCategory(registration.getJeiHelpers().getGuiHelper()));

        slotDrawable = guiHelper.getSlotDrawable();
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        final var recipeManager = Minecraft.getInstance().level.getRecipeManager();

        registration.addRecipes(ClocheRecipeCategory.RECIPE_TYPE,
                recipeManager.getAllRecipesFor(ClocheRecipes.CLOCHE_TYPE.get()).stream().map(RecipeHolder::value).toList());

        List<ItemStack> upgrades = new ArrayList<>();
        List<DimensionalUpgradeRecipe> dimensionalUpgradeRecipes =
                recipeManager.getAllRecipesFor(DimensionalUpgradeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).toList();
        dimensionalUpgradeRecipes.forEach(recipe -> upgrades.add(recipe.ingredient().getItems()[0]));
        registration.addRecipes(CLOCHE_UPGRADES, upgrades);
    }
}
