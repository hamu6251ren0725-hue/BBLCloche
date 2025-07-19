package com.benbenlaw.cloche.integration.jei;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.core.block.colored.util.IColored;
import com.benbenlaw.core.item.CoreDataComponents;
import com.benbenlaw.core.item.colored.ColoredItem;
import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.core.util.MouseUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (Block.byItem(recipe.seed().getItems()[0].getItem()) instanceof IColored || recipe.seed().getItems()[0].getItem() instanceof ColoredItem) {
            String color = recipe.seed().getItems()[0].get(CoreDataComponents.COLOR);
            ItemStack coloredStack = new ItemStack(recipe.seed().getItems()[0].getItem());
            coloredStack.set(CoreDataComponents.COLOR, color);
            builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addItemStack(coloredStack)
                    .addRichTooltipCallback((slotView, tooltip) -> {
                        if (!recipe.dimension().isEmpty()) {
                            tooltip.add(Component.translatable("jei.dimension.in")
                                    .append(Component.translatable(recipe.dimension()).withStyle(ChatFormatting.GOLD)));
                        }
                    });
        } else {
            builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.seed())
                    .addRichTooltipCallback((slotView, tooltip) -> {
                        if (!recipe.dimension().isEmpty()) {
                            tooltip.add(Component.translatable("jei.dimension.in")
                                    .append(Component.translatable(recipe.dimension()).withStyle(ChatFormatting.GOLD)));
                        }
                    });
        }


        builder.addSlot(RecipeIngredientRole.INPUT, 2, 20).addIngredients(recipe.soil());

        if (!recipe.catalyst().hasNoItems()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 36, 11).addIngredients(recipe.catalyst())
                    .setBackground(JEIClochePlugin.slotDrawable, -1, -1);
        } else {
            builder.addSlot(RecipeIngredientRole.CATALYST, 36, 11).addItemStack(Items.AIR.getDefaultInstance())
                    .setBackground(JEIClochePlugin.slotDrawable, -1, -1);
        }

        List<ChanceResult> modifiedOutputs = new ArrayList<>(recipe.getRollResults());
        if (!recipe.getShearsResult().isEmpty()) {
            modifiedOutputs.addLast(new ChanceResult(recipe.getShearsResult(), 1.0f));
        }

        int size = modifiedOutputs.size();
        int centerX = size > 0 ? 1 : 10;
        int centerY = size > 4 ? 1 : 10;
        int xOffset = 0;
        int yOffset = 0;
        int index = 0;

        for (int i = 0; i < size; i++) {
            xOffset = centerX + (i % 4) * 18;
            yOffset = centerY + ((i / 4) * 18);
            index = i;

            int finalIndex = index;
            builder.addSlot(RecipeIngredientRole.OUTPUT, 67 + xOffset, yOffset)
                    .addItemStack(modifiedOutputs.get(i).stack()).addRichTooltipCallback((slotView, tooltip) -> {
                        ChanceResult output = modifiedOutputs.get(finalIndex);
                        float chance = output.chance();
                        tooltip.add(Component.translatable("block.cloche.jei.chance")
                                .append(String.valueOf((int) (chance * 100)))
                                .append("%").withStyle(ChatFormatting.GOLD));
                        if (finalIndex == 0) {
                            tooltip.add(Component.translatable("block.cloche.jei.main_output")
                                    .withStyle(ChatFormatting.GREEN));
                        }
                        if (output.stack().is(recipe.getShearsResult().getItem())) {
                            tooltip.add(Component.translatable("block.cloche.jei.shears_result")
                                    .withStyle(ChatFormatting.GREEN));
                        }
                        if (recipe.getSeed().test(output.stack()) && finalIndex > 0) {
                            tooltip.add(Component.translatable("block.cloche.jei.seeds_result")
                                    .withStyle(ChatFormatting.GREEN));
                        }
                    }).setBackground(JEIClochePlugin.slotDrawable, -1, -1);
        }
    }

    @Override
    public void draw(ClocheRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        if (MouseUtil.isMouseAboveArea((int) mouseX, (int) mouseY, 0, 0, 19, 10, 16, 16)) {
            Font font = Minecraft.getInstance().font;
            int duration = recipe.getDuration();
            guiGraphics.renderTooltip(font, Component.translatable("block.cloche.jei.duration", duration), 2 + (int) mouseX, 6 + (int) mouseY);
        }

        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }
}
