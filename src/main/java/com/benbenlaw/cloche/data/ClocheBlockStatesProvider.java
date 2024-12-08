package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import com.google.gson.JsonElement;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClocheBlockStatesProvider extends BlockStateProvider {

    public ClocheBlockStatesProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cloche.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
       //blockWithItem(ClocheBlocks.CLOCHE);
    }


    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
    //    simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void fluidBlocks(String name, Block block) {
    //    simpleBlock(block, models().getBuilder(name).texture("particle", ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "block/" + name + "_still")));
    }

    private static PropertyDispatch createBooleanModelDispatch(BooleanProperty p_124623_, ResourceLocation p_124624_, ResourceLocation p_124625_) {
        return PropertyDispatch.property(p_124623_)
                .select(true, Variant.variant().with(VariantProperties.MODEL, p_124624_))
                .select(false, Variant.variant().with(VariantProperties.MODEL, p_124625_));
    }

    private static PropertyDispatch createHorizontalFacingDispatch() {
        return PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
                .select(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .select(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .select(Direction.NORTH, Variant.variant());
    }

    @Override
    public String getName() {
        return Cloche.MOD_ID + " Block States";
    }
}
