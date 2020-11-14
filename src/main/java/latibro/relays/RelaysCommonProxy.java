package latibro.relays;

import dan200.computercraft.api.ComputerCraftAPI;
import latibro.relays.computer.relaybox.ComputerRelayBoxBlock;
import latibro.relays.computer.relaybox.ComputerRelayBoxItem;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import latibro.relays.computer.relaybox.cc.ComputerRelayBoxPeripheralProvider;
import latibro.relays.computer.relaybox.oc.ComputerRelayBoxDriver;
import li.cil.oc.api.API;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RelaysCommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
        ComputerCraftAPI.registerPeripheralProvider(new ComputerRelayBoxPeripheralProvider());
        API.driver.add(new ComputerRelayBoxDriver());
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new ComputerRelayBoxBlock());
        GameRegistry.registerTileEntity(ComputerRelayBoxTileEntity.class, new ResourceLocation("computer_relay_box"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ComputerRelayBoxItem());
    }

}
