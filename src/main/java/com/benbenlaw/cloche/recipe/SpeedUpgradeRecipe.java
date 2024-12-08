package com.benbenlaw.cloche.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static com.benbenlaw.cloche.block.entity.ClocheBlockEntity.*;

public record SpeedUpgradeRecipe(Ingredient ingredient, String modifierType, int duration) implements Recipe<RecipeInput> {

    //String type is for type of modifier, percentage, fixed amount removed or new fixed total
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
        return SpeedUpgradeRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return SpeedUpgradeRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<SpeedUpgradeRecipe> {
        private Type() {}
        public static final SpeedUpgradeRecipe.Type INSTANCE = new SpeedUpgradeRecipe.Type();
    }

    public static class Serializer implements RecipeSerializer<SpeedUpgradeRecipe> {
        public static final SpeedUpgradeRecipe.Serializer INSTANCE = new SpeedUpgradeRecipe.Serializer();
        public final MapCodec<SpeedUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(SpeedUpgradeRecipe::ingredient),
                        Codec.STRING.fieldOf("modifier_type").forGetter(SpeedUpgradeRecipe::modifierType),
                        Codec.INT.fieldOf("modifier").forGetter(SpeedUpgradeRecipe::duration)
                ).apply(instance, SpeedUpgradeRecipe::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, SpeedUpgradeRecipe> STREAM_CODEC = StreamCodec.of(
                SpeedUpgradeRecipe.Serializer::write, SpeedUpgradeRecipe.Serializer::read);

        @Override
        public @NotNull MapCodec<SpeedUpgradeRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SpeedUpgradeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SpeedUpgradeRecipe read(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            String type = buffer.readUtf(Short.MAX_VALUE);
            int modifier = buffer.readInt();
            return new SpeedUpgradeRecipe(ingredient, type, modifier);
        }

        private static void write(RegistryFriendlyByteBuf buffer, SpeedUpgradeRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
            buffer.writeUtf(recipe.modifierType);
            buffer.writeInt(recipe.duration);
        }
    }
}
