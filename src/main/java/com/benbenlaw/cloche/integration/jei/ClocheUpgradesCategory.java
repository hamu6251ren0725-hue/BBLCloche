package com.benbenlaw.cloche.integration.jei;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClocheUpgradesCategory implements IRecipeCategory<ItemStack> {

    public final static ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "textures/gui/jei_dynamic.png");
    private IDrawable background;
    private final IDrawable icon;
    private final IGuiHelper helper;
    private int tabs_used = 0;
    public ClocheUpgradesCategory(IGuiHelper helper) {
        this.helper = helper;
        this.background = helper.createDrawable(TEXTURE, 0, 0, 140, 37);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ClocheBlocks.CLOCHE.get()));
    }
    @Override
    public RecipeType<ItemStack> getRecipeType() {
        return JEIClochePlugin.CLOCHE_UPGRADES;
    }

    @Override
    public boolean isHandled(ItemStack recipe) {
        return tabs_used == 0;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.cloche.upgrades");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    private final Map<Point, ItemStack> slotRecipes = new HashMap<>();
    private int backgroundWidth;

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemStack itemStack, IFocusGroup focusGroup) {
        tabs_used++;


    }
}

