package com.benbenlaw.cloche.recipe;

import com.benbenlaw.cloche.Cloche;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ClocheRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Cloche.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Cloche.MOD_ID);

    //Cloche
    public static final Supplier<RecipeSerializer<ClocheRecipe>> CLOCHE_SERIALIZER =
            SERIALIZER.register("cloche", () -> ClocheRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeType<ClocheRecipe>> CLOCHE_TYPE =
            TYPES.register("cloche", () -> ClocheRecipe.Type.INSTANCE);

    //Dimensional Upgrade
    public static final Supplier<RecipeSerializer<DimensionalUpgradeRecipe>> DIMENSIONAL_UPGRADE_SERIALIZER =
            SERIALIZER.register("dimensional_upgrade", () -> DimensionalUpgradeRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeType<DimensionalUpgradeRecipe>> DIMENSIONAL_UPGRADE_TYPE =
            TYPES.register("dimensional_upgrade", () -> DimensionalUpgradeRecipe.Type.INSTANCE);

    //Speed Upgrade
    public static final Supplier<RecipeSerializer<SpeedUpgradeRecipe>> SPEED_UPGRADE_SERIALIZER =
            SERIALIZER.register("speed_upgrade", () -> SpeedUpgradeRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeType<SpeedUpgradeRecipe>> SPEED_UPGRADE_TYPE =
            TYPES.register("speed_upgrade", () -> SpeedUpgradeRecipe.Type.INSTANCE);
    public static void register(IEventBus eventBus) {
        SERIALIZER.register(eventBus);
        TYPES.register(eventBus);
    }


}
