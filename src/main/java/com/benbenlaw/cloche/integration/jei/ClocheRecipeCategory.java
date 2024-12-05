package com.benbenlaw.cloche.integration.jei;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.core.recipe.ChanceResult;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class ClocheRecipeCategory implements IRecipeCategory<ClocheRecipe> {

    public final static ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "cloche");
    public final static ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "textures/gui/jei_cloche.png");
    static final RecipeType<ClocheRecipe> RECIPE_TYPE = RecipeType.create(Cloche.MOD_ID, "cloche",
            ClocheRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    @Override
    public @Nullable ResourceLocation getRegistryName(ClocheRecipe recipe) {
        assert Minecraft.getInstance().level != null;
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(ClocheRecipe.Type.INSTANCE).stream()
                .filter(recipeHolder -> recipeHolder.value().equals(recipe))
                .map(RecipeHolder::id)
                .findFirst()
                .orElse(null);
    }
    public ClocheRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 140, 37);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ClocheBlocks.CLOCHE.get()));
    }
    @Override
    public RecipeType<ClocheRecipe> getRecipeType() {
        return JEIClochePlugin.CLOCHE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.cloche.cloche");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ClocheRecipe recipe, IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.seed())
                .addRichTooltipCallback((slotView, tooltip) -> {
                    if (!recipe.dimension().isEmpty()) {
                        tooltip.add(Component.translatable("jei.dimension.in")
                                .append(Component.translatable(recipe.dimension()).withStyle(ChatFormatting.GOLD)));
                    }
                });

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 20).addIngredients(recipe.soil());

        if (!recipe.catalyst().hasNoItems()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 36, 11).addIngredients(recipe.catalyst())
                    .setBackground(JEIClochePlugin.slotDrawable, -1, -1);
        } else {
            builder.addSlot(RecipeIngredientRole.CATALYST, 36, 11).addItemStack(Items.AIR.getDefaultInstance())
                    .setBackground(JEIClochePlugin.slotDrawable, -1, -1);
        }


        NonNullList<ChanceResult> recipeOutputs = recipe.getRollResults();

        int size = recipeOutputs.size();
        int centerX = size > 0 ? 1 : 10;
        int centerY = size > 4 ? 1 : 10;

        for (int i = 0; i < size; i++) {
            int xOffset = centerX + (i % 4) * 18;
            int yOffset = centerY + ((i / 4) * 18);
            int index = i;

            builder.addSlot(RecipeIngredientRole.OUTPUT, 67 + xOffset, yOffset)
                    .addItemStack(recipeOutputs.get(i).stack()).addRichTooltipCallback((slotView, tooltip) -> {
                        ChanceResult output = recipeOutputs.get(index);
                        float chance = output.chance();
                            tooltip.add(Component.translatable("block.cloche.jei.chance")
                                    .append(String.valueOf((int) (chance * 100)))
                                    .append("%").withStyle(ChatFormatting.GOLD));
                    }).setBackground(JEIClochePlugin.slotDrawable, -1, -1);
            }
    }
}
