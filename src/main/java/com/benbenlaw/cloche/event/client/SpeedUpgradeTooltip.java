package com.benbenlaw.cloche.event.client;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import com.benbenlaw.cloche.recipe.SpeedUpgradeRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = Cloche.MOD_ID ,value = Dist.CLIENT)

public class SpeedUpgradeTooltip {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {

        ItemStack upgradeStack = event.getItemStack();
        List<Component> components = event.getToolTip();

        if (event.getEntity() == null) {
            return;
        }

        Level level = event.getEntity().level();

        List<RecipeHolder<SpeedUpgradeRecipe>> speedRecipe = level.getRecipeManager()
                .getAllRecipesFor(SpeedUpgradeRecipe.Type.INSTANCE);

        List<RecipeHolder<DimensionalUpgradeRecipe>> dimensionRecipes = level.getRecipeManager()
                .getAllRecipesFor(DimensionalUpgradeRecipe.Type.INSTANCE);

        // Speed Upgrade Tooltip, adds tooltip to the item if it is in a speed upgrade recipe
        for (RecipeHolder<SpeedUpgradeRecipe> recipeHolder : speedRecipe) {
            SpeedUpgradeRecipe speedUpgradeRecipe = recipeHolder.value();

            if (speedUpgradeRecipe.ingredient().test(upgradeStack)) {
                if (Screen.hasShiftDown()) {
                    MutableComponent newTooltip = getMutableComponent(speedUpgradeRecipe);
                    components.add(newTooltip.withStyle(ChatFormatting.YELLOW));
                }
                else {
                    components.add(Component.translatable("tooltips.bblcore.shift").withStyle(ChatFormatting.YELLOW));
                }
            }
        }

        // Dim Upgrade Tooltip, adds tooltip to the item is a dim upgrade recipe
        for (RecipeHolder<DimensionalUpgradeRecipe> recipeHolder : dimensionRecipes) {
            DimensionalUpgradeRecipe dimensionalUpgradeRecipe = recipeHolder.value();

            if (dimensionalUpgradeRecipe.ingredient().test(upgradeStack)) {
                if (Screen.hasShiftDown()) {
                    MutableComponent newTooltip = getMutableComponent(dimensionalUpgradeRecipe);
                    components.add(newTooltip.withStyle(ChatFormatting.YELLOW));
                    break;
                }
                else {
                    components.add(Component.translatable("tooltips.bblcore.shift").withStyle(ChatFormatting.YELLOW));
                }
            }
        }
    }

    @NotNull
    private static MutableComponent getMutableComponent(DimensionalUpgradeRecipe dimensionalUpgradeRecipe) {
        String dimensionName = dimensionalUpgradeRecipe.dimension(); // Assuming this method exists
        Component dimensionNameTranslated = Component.translatable(dimensionName);
        String translatedDimensionName = dimensionNameTranslated.getString();
        String displayDimensionName = translatedDimensionName.startsWith("The ")
                ? translatedDimensionName
                : "The " + translatedDimensionName;
        return Component.translatable(
                "tooltip.cloche.upgrade.dimension", displayDimensionName
        );
    }


    @NotNull
    private static MutableComponent getMutableComponent(SpeedUpgradeRecipe speedUpgradeRecipe) {
        int durationReduction = speedUpgradeRecipe.duration();
        String modifierType = speedUpgradeRecipe.modifierType();
        MutableComponent newTooltip = Component.empty();
        if (Objects.equals(modifierType, "percentage")) {
            newTooltip = Component.translatable(
                    "tooltip.cloche.upgrade.percentage_speed_upgrade", durationReduction);
        }
        if (Objects.equals(modifierType, "fixed")) {

            newTooltip = Component.translatable(
                    "tooltip.cloche.upgrade.fixed_speed_upgrade", durationReduction
            );
        }
        return newTooltip;
    }
}
