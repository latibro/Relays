package latibro.relays;

import latibro.relays.computer.relaybox.ComputerRelayBoxBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RelaysBlocks {

    @GameRegistry.ObjectHolder("relays:computer_relay_box")
    public static ComputerRelayBoxBlock computerRelayBox;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        computerRelayBox.initModel();
    }

}
