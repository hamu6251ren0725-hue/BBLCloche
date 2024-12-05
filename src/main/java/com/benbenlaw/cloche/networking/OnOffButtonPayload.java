package com.benbenlaw.cloche.networking;

import com.benbenlaw.cloche.Cloche;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record OnOffButtonPayload(BlockPos blockPos) implements CustomPacketPayload {

    public static final Type<OnOffButtonPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "on_off_button"));

    @Override
    public @NotNull Type<OnOffButtonPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, OnOffButtonPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, OnOffButtonPayload::blockPos,
            OnOffButtonPayload::new
    );

}
