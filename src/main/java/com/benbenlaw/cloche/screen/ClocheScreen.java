package com.benbenlaw.cloche.screen;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.block.ClocheBlock;
import com.benbenlaw.cloche.networking.OnOffButtonPayload;
import com.benbenlaw.cloche.util.ClocheTags;
import com.benbenlaw.core.screen.util.CoreButtons;
import com.benbenlaw.core.screen.util.TooltipArea;
import com.benbenlaw.core.util.MouseUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClocheScreen extends AbstractContainerScreen<ClocheMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Cloche.MOD_ID, "textures/gui/cloche.png");

    public ClocheScreen(ClocheMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 165;
        this.imageWidth = 175;
    }

    int x = (width - imageWidth) / 2;
    int y = (height - imageHeight) / 2;


    @Override
    protected void init() {
        super.init();
        addMenuButtons();
    }

    @Override
    protected void containerTick() {
        this.clearWidgets();
        addMenuButtons();
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (!menu.blockEntity.errorMessage.isEmpty()) {
            guiGraphics.blit(TEXTURE,x + 21, y - 17, 177, 17, 20, 18);
            renderErrorTooltip(guiGraphics, mouseX, mouseY, x, y);
        }
        if (menu.blockEntity.errorMessage.isEmpty()){
            guiGraphics.blit(TEXTURE, x + 21, y - 17, 177, 35, 20, 18);
            renderTickRate(guiGraphics, mouseX, mouseY, x, y);
        }
        if (menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 49, y + 25, 176, 0, menu.getScaledProgress(), 16);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
        renderSlotTooltips(guiGraphics, mouseX, mouseY, x, y);
    }

    private void renderSlotTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        List<TooltipArea> tooltipAreas = new ArrayList<>();

        tooltipAreas.add(new TooltipArea(8, 17, 16, 16, "block.cloche.gui.seed_slot"));
        tooltipAreas.add(new TooltipArea(8, 35, 16, 16, "block.cloche.gui.soil_slot"));
        tooltipAreas.add(new TooltipArea(35, 53, 16, 16, "block.cloche.gui.upgrade_slot"));
        tooltipAreas.add(new TooltipArea(53, 53, 16, 16, "block.cloche.gui.upgrade_slot"));
        tooltipAreas.add(new TooltipArea(71, 53, 16, 16, "block.cloche.gui.upgrade_slot"));


        if (menu.numberOfCatalysts == 0) {
            tooltipAreas.add(new TooltipArea(8, 53, 16, 16, "block.cloche.gui.catalyst_slot_no_catalysts"));
        } else {
            tooltipAreas.add(new TooltipArea(8, 53, 16, 16, "block.cloche.gui.catalyst_slot"));

        }

        for (TooltipArea area : tooltipAreas) {
            if (MouseUtil.isMouseAboveArea(mouseX, mouseY, x, y, area.offsetX, area.offsetY, area.width, area.height)) {
                if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && !this.hoveredSlot.hasItem()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable(area.translationKey), mouseX, mouseY);
                }
            }
        }

        if (MouseUtil.isMouseAboveArea(mouseX, mouseY, x, y, 0, -17, 19, 18)) {
            guiGraphics.renderTooltip(this.font, Component.translatable("block.cloche.gui.on_off"), mouseX, mouseY);
        }

    }

    @Nullable
    private void renderTickRate(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        if (MouseUtil.isMouseAboveArea(mouseX, mouseY, x, y, 20, -17, 19, 18)) {
            guiGraphics.renderTooltip(this.font, Component.literal(this.menu.getProgress() + "/" +
                    this.menu.getMaxProgress()), mouseX, mouseY);
        }
    }

    private void renderErrorTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        if (MouseUtil.isMouseAboveArea(mouseX, mouseY, x, y, 20, -17, 19, 18)) {
            guiGraphics.renderTooltip(this.font, Component.translatable(this.menu.blockEntity.errorMessage), mouseX, mouseY);
        }
    }

    private void addMenuButtons() {
        int buttonX = this.leftPos;
        int buttonY = this.topPos - 17;

        if (this.menu.blockEntity != null) {
            WidgetSprites buttonTexture = this.menu.blockEntity.getBlockState().getValue(ClocheBlock.POWERED)
                    ? CoreButtons.ON_BUTTONS
                    : CoreButtons.OFF_BUTTONS;
            this.addRenderableWidget(new ImageButton(buttonX, buttonY, 20, 18, buttonTexture, (pressed) ->
                    PacketDistributor.sendToServer(new OnOffButtonPayload(this.menu.blockEntity.getBlockPos()))));
        }
    }
}
