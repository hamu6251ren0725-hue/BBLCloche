package com.benbenlaw.cloche.block.entity;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlock;
import com.benbenlaw.cloche.block.ClocheBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ClocheBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Cloche.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClocheBlockEntity>> CLOCHE_BLOCK_ENTITY =
            register("cloche_block_entity", () ->
                    BlockEntityType.Builder.of(ClocheBlockEntity::new, ClocheBlocks.CLOCHE.get()));

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,
                ClocheBlockEntities.CLOCHE_BLOCK_ENTITY.get(), ClocheBlockEntity::getItemHandlerCapability);
    }

    public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(@Nonnull String name, @Nonnull Supplier<BlockEntityType.Builder<T>> initializer) {
        return BLOCK_ENTITIES.register(name, () -> initializer.get().build(null));
    }
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }




}
