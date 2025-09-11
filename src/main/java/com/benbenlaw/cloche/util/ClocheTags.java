package com.benbenlaw.cloche.util;

import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.core.util.CoreTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ClocheTags {

    public static class Blocks extends CoreTags.Blocks {

    }

    public static class Items extends CoreTags.Items {
        public static final TagKey<Item> UPGRADES = tag(Cloche.MOD_ID, "upgrades");
        public static final TagKey<Item> CATALYSTS = tag(Cloche.MOD_ID, "catalysts");
    }
}
