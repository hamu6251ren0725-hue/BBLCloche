package com.benbenlaw.cloche.item;

import com.benbenlaw.core.item.TooltipUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class UpgradeItem extends Item {
    private final String tooltip;

    public UpgradeItem(Properties properties, String tooltip) {
        super(properties);
        this.tooltip = tooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        TooltipUtil.addShiftTooltip(list, tooltip);
    }
}
