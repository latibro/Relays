package latibro.relays.computer.relaybox;

import latibro.relays.RelaysCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ComputerRelayBoxBlock extends Block implements ITileEntityProvider {

    public ComputerRelayBoxBlock() {
        super(Material.IRON);
        setRegistryName("computer_relay_box");
        setUnlocalizedName("relays.computer_relay_box");
        setCreativeTab(RelaysCreativeTabs.DEFAULT);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ComputerRelayBoxTileEntity();
    }
}
