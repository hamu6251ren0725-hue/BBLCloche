package com.benbenlaw.cloche.screen;

import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.block.entity.ClocheBlockEntity;
import com.benbenlaw.cloche.recipe.ClocheRecipes;
import com.benbenlaw.cloche.util.ClocheTags;
import com.benbenlaw.core.screen.util.CoreSlotTextures;
import com.benbenlaw.core.screen.util.slot.CoreSlot;
import com.benbenlaw.core.screen.util.slot.ResultSlot;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.benbenlaw.cloche.block.entity.ClocheBlockEntity.*;

public class ClocheMenu extends AbstractContainerMenu {
    protected ClocheBlockEntity blockEntity;
    protected Level level;
    protected ContainerData data;
    protected Player player;
    protected BlockPos blockPos;
    public int numberOfCatalysts = 0;

    public ClocheMenu(int containerID, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerID, inventory, extraData.readBlockPos(), new SimpleContainerData(2));
    }

    public ClocheMenu(int containerID, Inventory inventory, BlockPos blockPos, ContainerData data) {
        super(ClocheMenus.CLOCHE_MENU.get(), containerID);
        this.player = inventory.player;
        this.blockPos = blockPos;
        this.level = inventory.player.level();
        this.blockEntity = (ClocheBlockEntity) this.level.getBlockEntity(blockPos);
        this.data = data;

        ClocheBlockEntity entity = (ClocheBlockEntity) this.level.getBlockEntity(blockPos);

        checkContainerSize(inventory, 18);
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        assert entity != null;

        this.addSlot(new CoreSlot(entity.getItemStackHandler(), SEED_SLOT, 8, 17) {
            @Override
            public @NotNull Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.SEED_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidSeed(stack);
            }
        });

        this.addSlot(new CoreSlot(entity.getItemStackHandler(), SOIL_SLOT, 8, 35) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.BLOCK_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidSoil(stack);
            }
        });
        this.addSlot(new CoreSlot(entity.getItemStackHandler(), CATALYST_SLOT, 8, 53) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.CATALYST_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidCatalyst(stack);
            }
        });

        this.addSlot(new CoreSlot(entity.getItemStackHandler(), UPGRADE_SLOT_1, 35, 53) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.UPGRADE_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ClocheTags.Items.UPGRADES);
            }
        });

        this.addSlot(new CoreSlot(entity.getItemStackHandler(), UPGRADE_SLOT_2, 53, 53) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.UPGRADE_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ClocheTags.Items.UPGRADES);
            }
        });

        this.addSlot(new CoreSlot(entity.getItemStackHandler(), UPGRADE_SLOT_3, 71, 53) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CoreSlotTextures.UPGRADE_SLOT);
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ClocheTags.Items.UPGRADES);
            }
        });

        int OUTPUT_SLOT = 6;
        int xStart = 98;
        int yStart = 17;
        int xOffset = 18;
        int yOffset = 18;
        int rows = 3;
        int columns = 4;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int xPos = xStart + (col * xOffset);
                int yPos = yStart + (row * yOffset);
                this.addSlot(new ResultSlot(blockEntity.getItemStackHandler(), OUTPUT_SLOT++, xPos, yPos, 64));
            }
        }

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0 ;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getProgress() {
        return this.data.get(0);
    }

    public int getMaxProgress() {
        return this.data.get(1);
    }

    public Map<String, List<Ingredient>> getAllSoilOptions() {

        Map<String, List<Ingredient>> options = new HashMap<>();

        List<Ingredient> soilOptions = new ArrayList<>();
        List<Ingredient> seedOptions = new ArrayList<>();
        List<Ingredient> catalystOptions = new ArrayList<>();

        level.getRecipeManager().getAllRecipesFor(ClocheRecipes.CLOCHE_TYPE.get()).forEach(recipe -> {
            soilOptions.add(recipe.value().getSoil());
            seedOptions.add(recipe.value().getSeed());
            catalystOptions.add(recipe.value().getCatalyst());
        });

        numberOfCatalysts = catalystOptions.size();

        options.put("soil", soilOptions);
        options.put("seed", seedOptions);
        options.put("catalyst", catalystOptions);

        return options;
    }

    public boolean isValidSoil(ItemStack stack) {
        return getAllSoilOptions().get("soil").stream().anyMatch(ingredient -> ingredient.test(stack));
    }
    public boolean isValidSeed(ItemStack stack) {
        return getAllSoilOptions().get("seed").stream().anyMatch(ingredient -> ingredient.test(stack));
    }
    public boolean isValidCatalyst(ItemStack stack) {
        return getAllSoilOptions().get("catalyst").stream().anyMatch(ingredient -> ingredient.test(stack));
    }


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;



    private static final int TE_INVENTORY_SLOT_COUNT = 18;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {


        return stillValid(ContainerLevelAccess.create(player.level(), blockPos),
                player, ClocheBlocks.CLOCHE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
