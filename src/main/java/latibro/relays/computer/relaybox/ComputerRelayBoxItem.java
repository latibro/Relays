package latibro.relays.computer.relaybox;

import latibro.relays.RelaysBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ComputerRelayBoxItem extends ItemBlock {

    public ComputerRelayBoxItem() {
        super(RelaysBlocks.computerRelayBox);
        setRegistryName(block.getRegistryName());
    }

}
