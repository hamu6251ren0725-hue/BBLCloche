package com.benbenlaw.cloche.block.entity;

import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.cloche.recipe.ClocheRecipe;
import com.benbenlaw.cloche.recipe.DimensionalUpgradeRecipe;
import com.benbenlaw.cloche.screen.ClocheMenu;
import com.benbenlaw.core.block.entity.SyncableBlockEntity;
import com.benbenlaw.core.block.entity.handler.InputOutputItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.benbenlaw.cloche.block.ClocheBlock.POWERED;

public class ClocheBlockEntity extends SyncableBlockEntity implements MenuProvider {

    public final ContainerData data;
    public static SpeedUpgradeLogic speedUpgradeLogic = new SpeedUpgradeLogic();
    public int progress = 0;
    public int maxProgress = 1000000;
    public String errorMessage = "";
    public static final int SEED_SLOT = 0;
    public static final int SOIL_SLOT = 1;
    public static final int CATALYST_SLOT = 2;
    public static final int UPGRADE_SLOT_1 = 3;
    public static final int UPGRADE_SLOT_2 = 4;
    public static final int UPGRADE_SLOT_3 = 5;
    public static final int[] OUTPUT_SLOTS = {6,7,8,9,10,11,12,13,14,15,16,17};

    public ItemStack getSeed() {
        return itemHandler.getStackInSlot(SEED_SLOT);
    }
    public ItemStack getSoil() {
        return itemHandler.getStackInSlot(SOIL_SLOT);
    }
    public ItemStack getCatalyst() {
        return itemHandler.getStackInSlot(CATALYST_SLOT);
    }
    public ItemStack getUpgrade1() {
        return itemHandler.getStackInSlot(UPGRADE_SLOT_1);
    }
    public ItemStack getUpgrade2() {
        return itemHandler.getStackInSlot(UPGRADE_SLOT_2);
    }
    public ItemStack getUpgrade3() {
        return itemHandler.getStackInSlot(UPGRADE_SLOT_3);
    }
    public ItemStackHandler getItemStackHandler() {
        return itemHandler;
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(18) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            if (slot == SEED_SLOT || slot == SOIL_SLOT || slot == CATALYST_SLOT || slot == UPGRADE_SLOT_1 || slot == UPGRADE_SLOT_2 || slot == UPGRADE_SLOT_3) {
                return 1;
            }
            return slot;
        }
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            sync();
        }
    };

    private final IItemHandler clocheItemHandlerSide = new InputOutputItemHandler(itemHandler,
            (i, stack) -> false,
            this::isOutputSlot
    );

    private boolean isOutputSlot(int slot) {
        for (int outputSlot : OUTPUT_SLOTS) {
            if (slot == outputSlot) {
                return true;
            }
        }
        return false;
    }

    public IItemHandler getItemHandlerCapability(Direction side) {
        return clocheItemHandlerSide;
    }

    public void setHandler(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            this.itemHandler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    public ClocheBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ClocheBlockEntities.CLOCHE_BLOCK_ENTITY.get(), blockPos, blockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> ClocheBlockEntity.this.progress;
                    case 1 -> ClocheBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ClocheBlockEntity.this.progress = value;
                    case 1 -> ClocheBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.cloche.cloche");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int container, @NotNull Inventory inventory, @NotNull Player player) {
        return new ClocheMenu(container, inventory, this.getBlockPos(), data);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.put("inventory", this.itemHandler.serializeNBT(provider));
        compoundTag.putInt("progress", progress);
        compoundTag.putInt("maxProgress", maxProgress);
        compoundTag.putString("errorMessage", errorMessage);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        this.itemHandler.deserializeNBT(provider, compoundTag.getCompound("inventory"));
        progress = compoundTag.getInt("progress");
        maxProgress = compoundTag.getInt("maxProgress");
        errorMessage = compoundTag.getString("errorMessage");
        super.loadAdditional(compoundTag, provider);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick() {

        assert level != null;
        if (!level.isClientSide()) {

            //System.out.println(errorMessage);

            RecipeInput inventory = new RecipeInput() {
                @Override
                public @NotNull ItemStack getItem(int index) {
                    return itemHandler.getStackInSlot(index);
                }

                @Override
                public int size() {
                    return itemHandler.getSlots();
                }
            };

            Optional<RecipeHolder<ClocheRecipe>> match = level.getRecipeManager()
                    .getRecipeFor(ClocheRecipe.Type.INSTANCE, inventory, level);

            int recipeMaxDuration = 1000000;
            if (match.isPresent()) {
                recipeMaxDuration = match.get().value().getDuration();
            }

            sync();

            if (this.getBlockState().getValue(POWERED)) {

                if (match.isPresent()) {

                    maxProgress = speedUpgradeLogic.getNewDuration(recipeMaxDuration, itemHandler, level);

                    ClocheRecipe currentRecipe = match.get().value();
                    if (correctDimension(currentRecipe)) {

                        List<ItemStack> results = currentRecipe.rollResults(level.random);
                        if (!canFitResults(results)) {
                            errorMessage = "block.cloche.error.output_full";
                            return;
                        }
                        progress++;
                        errorMessage = "";
                        if (progress >= maxProgress) {
                            resetProgress();
                            fillOutputSlots(currentRecipe);
                        }
                    } else {
                        errorMessage = "block.cloche.error.wrong_dimension";
                        resetProgress();
                    }
                } else {
                    errorMessage = "block.cloche.error.no_recipe";
                    resetProgress();
                }
            } else {
                errorMessage = "block.cloche.error.not_on";
                resetProgress();
            }
        }
    }



    public boolean correctDimension(ClocheRecipe recipe) {
        assert level != null;
        String clocheDimension = String.valueOf(level.dimension().location());

        Optional<String> dimensionalUpgrade = getDimensionalUpgradeInfo();
        if (dimensionalUpgrade.isPresent()) {
            clocheDimension = dimensionalUpgrade.get();
        }
        return clocheDimension.equals(recipe.getDimension()) || Objects.equals(recipe.getDimension(), "all");
    }

    public ItemStack hasShearsUpgrade(ClocheRecipe recipe) {
        ItemStack leavesBlock = ItemStack.EMPTY;
        boolean hasUpgrade1 = itemHandler.getStackInSlot(UPGRADE_SLOT_1).is(ClocheItems.SHEARS_UPGRADE);
        boolean hasUpgrade2 = itemHandler.getStackInSlot(UPGRADE_SLOT_2).is(ClocheItems.SHEARS_UPGRADE);
        boolean hasUpgrade3 = itemHandler.getStackInSlot(UPGRADE_SLOT_3).is(ClocheItems.SHEARS_UPGRADE);
        if (hasUpgrade1 || hasUpgrade2 || hasUpgrade3) {

            if (!recipe.shearsResult().isEmpty()) {
                leavesBlock = recipe.shearsResult().copy();
            }
        }
        return leavesBlock;
    }
    public Optional<String> getDimensionalUpgradeInfo() {
        RecipeInput inventory = new RecipeInput() {
            @Override
            public @NotNull ItemStack getItem(int index) {
                return itemHandler.getStackInSlot(index);
            }
            @Override
            public int size() {
                return itemHandler.getSlots();
            }
        };
        Optional<RecipeHolder<DimensionalUpgradeRecipe>> match = level.getRecipeManager()
                .getRecipeFor(DimensionalUpgradeRecipe.Type.INSTANCE, inventory, level);
        return match.map(recipeHolder -> recipeHolder.value().dimension());
    }

    public void resetProgress() {
        progress = 0;
    }

    public boolean canFitResults(List<ItemStack> results) {
        for (ItemStack result : results) {
            int remainingToFit = result.getCount();
            for (int outputSlot : OUTPUT_SLOTS) {
                ItemStack slotStack = itemHandler.getStackInSlot(outputSlot);
                if (slotStack.isEmpty()) {
                    remainingToFit = 0;
                    break;
                } else if (ItemStack.isSameItem(slotStack, result)) {
                    int maxStackSize = slotStack.getMaxStackSize();
                    int spaceLeft = maxStackSize - slotStack.getCount();

                    if (spaceLeft > 0) {
                        remainingToFit -= spaceLeft;
                        if (remainingToFit <= 0) {
                            break;
                        }
                    }
                }
            }
            if (remainingToFit > 0) {
                return false;
            }
        }
        return true;
    }

    public ItemStack hasNoSeedsUpgrade() {

        ItemStack seed = ItemStack.EMPTY;
        boolean hasUpgrade1 = itemHandler.getStackInSlot(UPGRADE_SLOT_1).is(ClocheItems.NO_SEEDS_UPGRADE);
        boolean hasUpgrade2 = itemHandler.getStackInSlot(UPGRADE_SLOT_2).is(ClocheItems.NO_SEEDS_UPGRADE);
        boolean hasUpgrade3 = itemHandler.getStackInSlot(UPGRADE_SLOT_3).is(ClocheItems.NO_SEEDS_UPGRADE);
        if (hasUpgrade1 || hasUpgrade2 || hasUpgrade3) {
            seed = itemHandler.getStackInSlot(SEED_SLOT);
        }

        return seed;
    }

    public int getMainOutputUpgradeCount() {
        int mainOutputUpgradeCount = 0;
        if (itemHandler.getStackInSlot(UPGRADE_SLOT_1).is(ClocheItems.MAIN_OUTPUT_UPGRADE)) {
            mainOutputUpgradeCount++;
        }
        if (itemHandler.getStackInSlot(UPGRADE_SLOT_2).is(ClocheItems.MAIN_OUTPUT_UPGRADE)) {
            mainOutputUpgradeCount++;
        }
        if (itemHandler.getStackInSlot(UPGRADE_SLOT_3).is(ClocheItems.MAIN_OUTPUT_UPGRADE)) {
            mainOutputUpgradeCount++;
        }
        return mainOutputUpgradeCount;
    }

    public void fillOutputSlots(ClocheRecipe recipe) {
        assert level != null;

        ItemStack seedToRemove = hasNoSeedsUpgrade();
        List<ItemStack> results = recipe.rollResults(level.random);

        // Main Output Upgrade, increase the main output (first item in the results list) by the upgrade count
        int mainOutputUpgradeCount = getMainOutputUpgradeCount();
        if (!results.isEmpty() && mainOutputUpgradeCount > 0) {
            results.getFirst().grow(mainOutputUpgradeCount);
        }

        // Shears Upgrade, add leaves block to output
        ItemStack leavesBlock = hasShearsUpgrade(recipe);
        if (!leavesBlock.isEmpty()) {
            results.add(hasShearsUpgrade(recipe));
        }

        for (ItemStack result : results) {
            // No Seed Upgrade, remove seed from output unless there is only the "seed" item in the output list
            if (!seedToRemove.isEmpty() && ItemStack.isSameItem(seedToRemove, result) && results.size() > 1) {
                continue;
            }

            for (int outputSlot : OUTPUT_SLOTS) {
                ItemStack slotStack = itemHandler.getStackInSlot(outputSlot);
                if (slotStack.isEmpty()) {
                    itemHandler.setStackInSlot(outputSlot, result.copy());
                    result.setCount(0);
                    break;
                } else if (ItemStack.isSameItem(slotStack, result)) {
                    int maxStackSize = slotStack.getMaxStackSize();
                    int spaceLeft = maxStackSize - slotStack.getCount();
                    if (spaceLeft > 0) {
                        int toAdd = Math.min(spaceLeft, result.getCount());
                        slotStack.grow(toAdd);
                        result.shrink(toAdd);
                        if (result.isEmpty()) {
                            break;
                        }
                    }
                }
            }
        }
    }

}
