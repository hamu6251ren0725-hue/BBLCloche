package com.benbenlaw.cloche.networking;

import com.benbenlaw.cloche.block.ClocheBlock;
import com.benbenlaw.cloche.block.ClocheBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record OnOffButtonPacket() {

    public static final OnOffButtonPacket INSTANCE = new OnOffButtonPacket();

    public static OnOffButtonPacket get() {
        return INSTANCE;
    }

    public void handle(final OnOffButtonPayload payload, IPayloadContext context) {

        Player player = context.player();
        Level level = player.level();
        BlockPos blockPos = payload.blockPos();
        BlockState blockState = level.getBlockState(blockPos);

        //Cloche On Off Button
        if (blockState.getBlock() instanceof ClocheBlock) {

            if (blockState.getValue(ClocheBlock.POWERED)) {
                level.setBlockAndUpdate(blockPos, ClocheBlocks.CLOCHE.get().defaultBlockState().setValue(ClocheBlock.POWERED, false)
                        .setValue(ClocheBlock.FACING, blockState.getValue(ClocheBlock.FACING)));
            } else {
                level.setBlockAndUpdate(blockPos, ClocheBlocks.CLOCHE.get().defaultBlockState().setValue(ClocheBlock.POWERED, true)
                        .setValue(ClocheBlock.FACING, blockState.getValue(ClocheBlock.FACING)));
            }
        }
    }
}