package com.benbenlaw.cloche.block;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.item.ClocheItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ClocheBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cloche.MOD_ID);


    public static final DeferredBlock<Block> CLOCHE = registerBlock("cloche",
            () -> new ClocheBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));







    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ClocheItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
