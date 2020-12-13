package latibro.relays;

import latibro.relays.computer.relaybox.ComputerRelayBoxItem;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RelaysItems {

    @GameRegistry.ObjectHolder("relays:computer_relay_box")
    public static ComputerRelayBoxItem computerRelayBox;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        computerRelayBox.initModel();
    }

}
