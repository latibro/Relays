package latibro.relays;

import latibro.relays.computer.relaybox.ComputerRelayBoxItem;
import latibro.relays.tools.EntityParingToolItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RelaysItems {

    @GameRegistry.ObjectHolder("relays:computer_relay_box")
    public static ComputerRelayBoxItem computerRelayBox;

    @GameRegistry.ObjectHolder("relays:entity_paring_tool")
    public static EntityParingToolItem entityParingTool;

}
