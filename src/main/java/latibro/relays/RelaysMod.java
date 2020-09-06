package latibro.relays;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = RelaysMod.MODID,
        name = RelaysMod.NAME,
        version = RelaysMod.VERSION,
        dependencies = "required-after:forge; " +
                "required-after: immersiverailroading;" +
                "after: opencomputers;" +
                "after: computercraft"
)
public class RelaysMod {

    public static final String MODID = "relays";
    public static final String NAME = "Relays";
    public static final String VERSION = "0.0.1";

    @Mod.Instance
    public static RelaysMod instance;

    public static Logger logger;

    @SidedProxy(clientSide = "latibro.relays.RelaysClientProxy", serverSide = "latibro.relays.RelaysServerProxy")
    public static RelaysCommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.trace("preInit mod");
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.trace("init mod");
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.trace("preInit mod");
        proxy.postInit(event);
    }

}
