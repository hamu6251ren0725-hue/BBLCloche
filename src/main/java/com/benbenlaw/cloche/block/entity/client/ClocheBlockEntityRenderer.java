package com.benbenlaw.cloche.block.entity.client;

import com.benbenlaw.cloche.block.entity.ClocheBlockEntity;
import com.benbenlaw.core.block.brightable.BrightBlock;
import com.benbenlaw.core.block.brightable.BrightSapling;
import com.benbenlaw.core.block.colored.ColoredBlock;
import com.benbenlaw.core.block.colored.util.ColorMap;
import com.benbenlaw.core.block.colored.util.IColored;
import com.benbenlaw.core.config.ColorTintIndexConfig;
import com.benbenlaw.core.item.CoreDataComponents;
import com.benbenlaw.core.util.ColorHandler;
import com.benbenlaw.core.util.CoreTags;
import com.benbenlaw.core.util.RenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.List;

public class ClocheBlockEntityRenderer implements BlockEntityRenderer<ClocheBlockEntity> {
    public ClocheBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(ClocheBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
        ItemStack soilStack = pBlockEntity.getSoil();
        ItemStack seedStack = pBlockEntity.getSeed();

        BlockState soilAsBlock;


        if (soilStack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();

            // Need to handle blocks that have a non 16x16x16 model shape, like farmland
            BlockState soilState;
            if (block instanceof FarmBlock) {
                soilState = block.defaultBlockState();
            } else {
                soilState = block.defaultBlockState();
            }

            // Get the model and top quads
            BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(soilState);
            RandomSource consistentRandom = RandomSource.create(pBlockEntity.getBlockPos().getX());
            List<BakedQuad> topQuads = model.getQuads(soilState, Direction.UP, consistentRandom, ModelData.builder().build(), RenderType.cutout());

            // Get BlockColors for tinting
            int color = Minecraft.getInstance().getBlockColors().getColor(soilState, null, pBlockEntity.getBlockPos(), -1);
            float red = (color >> 16 & 0xFF) / 255.0F;
            float green = (color >> 8 & 0xFF) / 255.0F;
            float blue = (color & 0xFF) / 255.0F;

            VertexConsumer buffer = pBufferSource.getBuffer(RenderType.cutout());

            pPoseStack.pushPose();
            pPoseStack.translate(0.05f, -0.92f, 0.05f);
            pPoseStack.scale(0.9f, 1.0f, 0.9f);

            // Render top quads only
            for (BakedQuad quad : topQuads) {
                buffer.putBulkData(pPoseStack.last(), quad, red, green, blue, 1.0f, pPackedLight, pPackedOverlay);
            }

            pPoseStack.popPose();
        }



        if (soilStack.getItem() instanceof BucketItem bucketItem) {

            Fluid fluid = bucketItem.content.defaultFluidState().getType();
            VertexConsumer buffer = pBufferSource.getBuffer(Sheets.translucentCullBlockSheet());

            renderFluid(pPoseStack.last(), buffer, pBlockEntity, fluid, 0.05F, pPackedLight);

        }


        BlockState seedAsBlock;
        int progress = pBlockEntity.progress;
        int maxProgress = pBlockEntity.maxProgress;

        if (seedStack.getItem() instanceof BlockItem blockItem) {
            seedAsBlock = blockItem.getBlock().defaultBlockState();

            int tintColor = 0xFFFFFF;

            if (blockItem.getBlock() instanceof IColored) {
                String colorName = seedStack.get(CoreDataComponents.COLOR.get());
                if (colorName != null) {

                    DyeColor dyeColor = DyeColor.valueOf(colorName.toUpperCase());
                    tintColor = ColorMap.getColorValue(dyeColor);

                }
            }

            if (blockItem.getBlock() instanceof BrightSapling) {

                ItemStack stack = pBlockEntity.getSeed();

                for (String colorTag : CoreTags.Blocks.COLOR_TAGS.keySet()) {
                    if (stack.is(CoreTags.Items.COLOR_TAGS.get(colorTag))) {
                        DyeColor dyeColor = DyeColor.valueOf(colorTag.toUpperCase());
                        tintColor = ColorMap.getColorValue(dyeColor);

                    }
                }
            }

            if (seedAsBlock.getBlock() instanceof CropBlock ageCropBlock) {
                int maxAge = ageCropBlock.getMaxAge();
                int age = Math.round((float) progress / maxProgress * maxAge);

                // Determine the correct age property
                Property<?> ageProperty = null;
                for (Property<?> property : ageCropBlock.getStateDefinition().getProperties()) {
                    if (property instanceof IntegerProperty integerProperty) {
                        if (integerProperty.getPossibleValues().contains(age)) {
                            ageProperty = integerProperty;
                            break;
                        }
                    }
                }

                if (ageProperty instanceof IntegerProperty integerProperty) {
                    BlockState seedAsBlockCrop = seedAsBlock.setValue(integerProperty, age);

                    pPoseStack.pushPose();
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.translate(0.1f, 0.1f, 0.1f);

                    renderWithTint(blockRenderDispatcher, seedAsBlockCrop, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, tintColor);

                    pPoseStack.popPose();
                }
            }


            else if (seedAsBlock.getBlock() instanceof StemBlock) {
                int maxAge = 7;
                int age = Math.round((float) progress / maxProgress * maxAge);
                age = Math.min(age, maxAge);
                BlockState seedAsBlockCrop = seedAsBlock.setValue(BlockStateProperties.AGE_7, age);

                pPoseStack.pushPose();
                pPoseStack.scale(0.8f, 0.8f, 0.8f);
                pPoseStack.translate(0.1f, 0.1f, 0.1f);

                renderWithTint(blockRenderDispatcher, seedAsBlockCrop, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, tintColor);

                pPoseStack.popPose();

            } else {

                pPoseStack.pushPose();

                float growthProgress = progress / (float) maxProgress;
                float minScale = 0.3f;
                float maxScale = 0.6f;
                float scale = minScale + growthProgress * (maxScale - minScale);

                pPoseStack.translate(0.5f, 0.1f, 0.5f);

                pPoseStack.scale(scale, scale, scale);

                pPoseStack.translate(-0.5f, 0.0f, -0.5f);

                renderWithTint(blockRenderDispatcher, seedAsBlock, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, tintColor);

                pPoseStack.popPose();
            }
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private void renderWithTint(BlockRenderDispatcher blockRenderDispatcher, BlockState state,
                                PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, int tintColor) {
        float r = (tintColor >> 16 & 0xFF) / 255.0F;
        float g = (tintColor >> 8 & 0xFF) / 255.0F;
        float b = (tintColor & 0xFF) / 255.0F;

        blockRenderDispatcher.getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.cutout()),
                state,
                blockRenderDispatcher.getBlockModel(state),
                r, g, b,
                packedLight,
                packedOverlay,
                ModelData.EMPTY,
                null
        );
    }

    private static void renderFluid(PoseStack.Pose pose, VertexConsumer consumer, BlockEntity entity, Fluid fluid, float fillAmount, int packedLight) {
        int color = IClientFluidTypeExtensions.of(fluid).getTintColor(fluid.defaultFluidState(), entity.getLevel(), entity.getBlockPos());
        //if (color == -1) color = 0xffffff;
        renderFluid(pose, consumer, fluid, fillAmount, color, packedLight);
    }


    public static void renderFluid(PoseStack.Pose pose, VertexConsumer consumer, Fluid fluid, float fillAmount, int color, int packedLight) {
        // Get fluid texture
        IClientFluidTypeExtensions props = IClientFluidTypeExtensions.of(fluid);
        TextureAtlasSprite texture = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(props.getStillTexture());

        // Get sizes
        float fluidHeight = (14 * fillAmount) / 16.0f;
        float inset = 0.0625F;
        float faceSize = 14 / 16.0f;


        // Sides
        RenderUtil.renderFace(Direction.SOUTH, pose, consumer, texture, inset, inset, inset, faceSize, fluidHeight, color, packedLight);
        RenderUtil.renderFace(Direction.NORTH, pose, consumer, texture, inset, inset, inset, faceSize, fluidHeight, color, packedLight);
        RenderUtil.renderFace(Direction.EAST, pose, consumer, texture, inset, inset, inset, faceSize, fluidHeight, color, packedLight);
        RenderUtil.renderFace(Direction.WEST, pose, consumer, texture, inset, inset, inset, faceSize, fluidHeight, color, packedLight);
        RenderUtil.renderFace(Direction.UP, pose, consumer, texture, inset, inset, inset + fluidHeight, faceSize, faceSize, color, packedLight);


        RenderUtil.renderFace(Direction.DOWN, pose, consumer, texture, inset, inset, 1 - inset , faceSize, faceSize, color, packedLight);
    }
}