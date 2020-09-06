package latibro.relays.computer.relaybox;

import latibro.relays.RelaysCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ComputerRelayBoxBlock extends Block {

    public ComputerRelayBoxBlock() {
        super(Material.IRON);
        setRegistryName("computer_relay_box");
        setUnlocalizedName("relays.computer_relay_box");
        setCreativeTab(RelaysCreativeTabs.DEFAULT);
    }

}
