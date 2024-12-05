package com.benbenlaw.cloche.data;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ClocheBlockStatesProvider extends BlockStateProvider {

    public ClocheBlockStatesProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cloche.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ClocheBlocks.CLOCHE);
    }


    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void fluidBlocks(String name, Block block) {
        simpleBlock(block, models().getBuilder(name).texture("particle", ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "block/" + name + "_still")));
    }


    @Override
    public String getName() {
        return Cloche.MOD_ID + " Block States";
    }
}
