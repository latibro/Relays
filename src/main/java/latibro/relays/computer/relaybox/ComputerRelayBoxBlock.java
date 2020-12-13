package latibro.relays.computer.relaybox;

import latibro.relays.RelaysCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
