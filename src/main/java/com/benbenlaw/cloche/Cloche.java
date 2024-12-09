package com.benbenlaw.cloche;

import com.benbenlaw.cloche.block.ClocheBlocks;
import com.benbenlaw.cloche.block.entity.ClocheBlockEntities;
import com.benbenlaw.cloche.item.ClocheCreativeModTab;
import com.benbenlaw.cloche.item.ClocheItems;
import com.benbenlaw.cloche.networking.ClocheMessages;
import com.benbenlaw.cloche.recipe.ClocheRecipes;
import com.benbenlaw.cloche.screen.ClocheMenus;
import com.benbenlaw.cloche.screen.ClocheScreen;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.apache.logging.log4j.LogManager;

@Mod(Cloche.MOD_ID)
public class Cloche{

    public static final String MOD_ID = "cloche";
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public Cloche(final IEventBus modEventBus, final ModContainer modContainer) {

        ClocheBlocks.BLOCKS.register(modEventBus);
        ClocheBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ClocheCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);
        ClocheItems.ITEMS.register(modEventBus);
        ClocheRecipes.SERIALIZER.register(modEventBus);
        ClocheRecipes.TYPES.register(modEventBus);
        ClocheMenus.MENUS.register(modEventBus);

        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);


    //    FlightBlocksItems.ITEMS.register(eventBus);
    //    FlightBlocksBlocks.BLOCKS.register(eventBus);
    //    FlightBlockEntities.BLOCK_ENTITIES.register(eventBus);

    //    modContainer.registerConfig(ModConfig.Type.STARTUP, StartupConfig.SPEC, "bbl/flightblocks/startup.toml");

    //    eventBus.addListener(this::addItemToCreativeTab);
    }

    private void addItemToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
   //         event.accept(FlightBlocksBlocks.FLIGHT_BLOCK.get());
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        ClocheBlockEntities.registerCapabilities(event);
    }

    @EventBusSubscriber(modid = Cloche.MOD_ID, bus = EventBusSubscriber.Bus.MOD ,value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ClocheMenus.CLOCHE_MENU.get(), ClocheScreen::new);
        }
    }

    public void commonSetup(RegisterPayloadHandlersEvent event) {
        ClocheMessages.registerNetworking(event);
    }

}
