package latibro.relays.computer.relaybox;

import latibro.relays.RelaysBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ComputerRelayBoxItem extends ItemBlock {

    public ComputerRelayBoxItem() {
        super(RelaysBlocks.computerRelayBox);
        setRegistryName(block.getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
