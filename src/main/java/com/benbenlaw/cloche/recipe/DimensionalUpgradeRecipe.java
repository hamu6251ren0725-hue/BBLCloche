package com.benbenlaw.cloche.recipe;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.core.recipe.NoInventoryRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static com.benbenlaw.cloche.block.entity.ClocheBlockEntity.*;

public record DimensionalUpgradeRecipe (Ingredient ingredient, String dimension) implements Recipe<RecipeInput> {
    @Override
    public boolean matches(@NotNull RecipeInput container, @NotNull Level level) {

        ItemStack upgradeSlot1 = container.getItem(UPGRADE_SLOT_1);
        ItemStack upgradeSlot2 = container.getItem(UPGRADE_SLOT_2);
        ItemStack upgradeSlot3 = container.getItem(UPGRADE_SLOT_3);

        return ingredient.test(upgradeSlot1) || ingredient.test(upgradeSlot2) || ingredient.test(upgradeSlot3);
    }

    @Override
    public ItemStack assemble(RecipeInput p_345149_, HolderLookup.Provider p_346030_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DimensionalUpgradeRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return DimensionalUpgradeRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<DimensionalUpgradeRecipe> {
        private Type() {}
        public static final DimensionalUpgradeRecipe.Type INSTANCE = new DimensionalUpgradeRecipe.Type();
    }

    public static class Serializer implements RecipeSerializer<DimensionalUpgradeRecipe> {
        public static final DimensionalUpgradeRecipe.Serializer INSTANCE = new DimensionalUpgradeRecipe.Serializer();
        public final MapCodec<DimensionalUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(DimensionalUpgradeRecipe::ingredient),
                        Codec.STRING.fieldOf("dimension").forGetter(DimensionalUpgradeRecipe::dimension)
                ).apply(instance, DimensionalUpgradeRecipe::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, DimensionalUpgradeRecipe> STREAM_CODEC = StreamCodec.of(
                DimensionalUpgradeRecipe.Serializer::write, DimensionalUpgradeRecipe.Serializer::read);

        @Override
        public @NotNull MapCodec<DimensionalUpgradeRecipe> codec() {
            return CODEC;
        }
        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DimensionalUpgradeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static DimensionalUpgradeRecipe read(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            String dimension = buffer.readUtf(Short.MAX_VALUE);
            return new DimensionalUpgradeRecipe(ingredient, dimension);
        }

        private static void write(RegistryFriendlyByteBuf buffer, DimensionalUpgradeRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
            buffer.writeUtf(recipe.dimension);
        }
    }
}
