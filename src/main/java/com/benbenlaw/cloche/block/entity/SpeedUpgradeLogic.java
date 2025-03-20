package com.benbenlaw.cloche.block.entity;

import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import com.benbenlaw.cloche.recipe.SpeedUpgradeRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.*;

public class SpeedUpgradeLogic {

    public int getNewDuration(int recipeStartAmount, ItemStackHandler itemHandler, Level level) {

        List<RecipeHolder<SpeedUpgradeRecipe>> speedRecipe = level.getRecipeManager()
                .getAllRecipesFor(SpeedUpgradeRecipe.Type.INSTANCE);

        double adjustedDuration = recipeStartAmount;

        ItemStack upgradeSlot1 = itemHandler.getStackInSlot(ClocheBlockEntity.UPGRADE_SLOT_1);
        ItemStack upgradeSlot2 = itemHandler.getStackInSlot(ClocheBlockEntity.UPGRADE_SLOT_2);
        ItemStack upgradeSlot3 = itemHandler.getStackInSlot(ClocheBlockEntity.UPGRADE_SLOT_3);

        ItemStack[] upgradeSlots = {upgradeSlot1, upgradeSlot2, upgradeSlot3};

        double totalPercentageReduction = 0.0;
        int totalFixedReduction = 0;

        for (ItemStack upgradeSlot : upgradeSlots) {
            if (upgradeSlot.isEmpty()) continue;

            for (RecipeHolder<SpeedUpgradeRecipe> recipeHolder : speedRecipe) {
                SpeedUpgradeRecipe speedUpgradeRecipe = recipeHolder.value();

                if (speedUpgradeRecipe.ingredient().test(upgradeSlot)) {
                    String modifierType = speedUpgradeRecipe.modifierType();
                    if (Objects.equals(modifierType, "percentage")) {
                        totalPercentageReduction += speedUpgradeRecipe.duration();
                    }

                    if (Objects.equals(modifierType, "fixed")) {
                        totalFixedReduction += speedUpgradeRecipe.duration();
                    }
                }
            }
        }

        adjustedDuration -= (recipeStartAmount * (totalPercentageReduction / 100.0));
        adjustedDuration -= totalFixedReduction;

        return adjustedDuration < 1 ? Integer.MAX_VALUE : (int) Math.round(adjustedDuration);
    }
}