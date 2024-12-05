package com.benbenlaw.cloche.recipe;

import com.benbenlaw.core.recipe.ChanceResult;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.benbenlaw.cloche.block.entity.ClocheBlockEntity.*;

public record ClocheRecipe (
        Ingredient seed,
        Ingredient soil,
        Ingredient catalyst,
        String dimension,
        int duration,
        NonNullList<ChanceResult> results) implements Recipe<RecipeInput> {

        @Override
        public boolean matches(RecipeInput container, @NotNull Level level) {

                boolean needCatalyst = !catalyst.isEmpty();
                if (needCatalyst) {
                        if (catalyst.test(container.getItem(CATALYST_SLOT))) {
                                return seed.test(container.getItem(SEED_SLOT)) && soil.test(container.getItem(SOIL_SLOT));
                        } else {
                                return false;
                        }
                }

                return seed.test(container.getItem(SEED_SLOT)) && soil.test(container.getItem(SOIL_SLOT));

        }

        @Override
        public @NotNull ItemStack assemble(@NotNull RecipeInput input, HolderLookup.@NotNull Provider provider) {
                return results.getFirst().stack().copy();
        }

        @Override
        public boolean canCraftInDimensions(int x, int y) {
                return true;
        }

        @Override
        public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
                return results.getFirst().stack();
        }

        public List<ItemStack> getResults() {
                return getRollResults().stream()
                        .map(ChanceResult::stack)
                        .collect(Collectors.toList());
        }

        public NonNullList<ChanceResult> getRollResults() {
                return this.results;
        }

        public List<ItemStack> rollResults(RandomSource rand) {
                List<ItemStack> results = new ArrayList<>();
                List<ChanceResult> rollResults = getRollResults();
                for (ChanceResult output : rollResults) {
                        ItemStack stack = output.rollOutput(rand);
                        if (!stack.isEmpty())
                                results.add(stack);
                }
                return results;
        }
        public Ingredient getSeed() {
                return seed;
        }
        public Ingredient getSoil() {
                return soil;
        }
        public Ingredient getCatalyst() {
                return catalyst;
        }
        public String getDimension() {
                return dimension;
        }
        public int getDuration() {
                return duration;
        }
        @Override
        public @NotNull RecipeSerializer<?> getSerializer() {
                return ClocheRecipe.Serializer.INSTANCE;
        }

        @Override
        public @NotNull RecipeType<?> getType() {
                return ClocheRecipe.Type.INSTANCE;
        }

        public static class Type implements RecipeType<ClocheRecipe> {
                private Type() {}
                public static final ClocheRecipe.Type INSTANCE = new ClocheRecipe.Type();
        }

        public static class Serializer implements RecipeSerializer<ClocheRecipe> {
                public static final ClocheRecipe.Serializer INSTANCE = new Serializer();
                public final MapCodec<ClocheRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                        instance.group(
                                Ingredient.CODEC.fieldOf("seed").forGetter(ClocheRecipe::seed),
                                Ingredient.CODEC.fieldOf("soil").forGetter(ClocheRecipe::soil),
                                Ingredient.CODEC.fieldOf("catalyst").orElse(Ingredient.EMPTY).forGetter(ClocheRecipe::catalyst),
                                Codec.STRING.optionalFieldOf("dimension", "all").forGetter(ClocheRecipe::dimension),
                                Codec.INT.fieldOf("duration").forGetter(ClocheRecipe::duration),
                                Codec.list(ChanceResult.CODEC).fieldOf("results").flatXmap(chanceResults -> {
                                        if (chanceResults.size() > 8) {
                                                return DataResult.error(
                                                        () -> "Too many results for cutting recipe! The maximum quantity of unique results is "
                                                                + 8);
                                        }
                                        NonNullList<ChanceResult> nonNullList = NonNullList.create();
                                        nonNullList.addAll(chanceResults);
                                        return DataResult.success(nonNullList);
                                }, DataResult::success).forGetter(ClocheRecipe::getRollResults)
                        ).apply(instance, ClocheRecipe::new)
                );

                private final StreamCodec<RegistryFriendlyByteBuf, ClocheRecipe> STREAM_CODEC = StreamCodec.of(
                        Serializer::write, Serializer::read);
                @Override
                public @NotNull MapCodec<ClocheRecipe> codec() {
                        return CODEC;
                }
                @Override
                public @NotNull StreamCodec<RegistryFriendlyByteBuf, ClocheRecipe> streamCodec() {
                        return STREAM_CODEC;
                }
                private static ClocheRecipe read(RegistryFriendlyByteBuf buffer) {
                        Ingredient seed = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                        Ingredient soil = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                        Ingredient catalyst = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                        String dimension = buffer.readUtf(Short.MAX_VALUE);
                        int duration = buffer.readInt();
                        int size = buffer.readVarInt();
                        NonNullList<ChanceResult> outputs = NonNullList.withSize(size, ChanceResult.EMPTY);
                        outputs.replaceAll(ignored -> ChanceResult.read(buffer));
                        return new ClocheRecipe(seed, soil, catalyst, dimension, duration, outputs);
                }
                private static void write(RegistryFriendlyByteBuf buffer, ClocheRecipe recipe) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.seed);
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.soil);
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.catalyst);
                        buffer.writeUtf(recipe.dimension, Short.MAX_VALUE);
                        buffer.writeInt(recipe.duration);
                        buffer.writeVarInt(recipe.results.size());
                        for (ChanceResult output : recipe.results) {
                                output.write(buffer);
                        }
                }
        }

}

