package latibro.relays.computer.relaybox;

import latibro.relays.RelaysCreativeTabs;
import latibro.relays.tools.EntityParingToolItem;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
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

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!EnumHand.MAIN_HAND.equals(hand)) {
            return false;
        }

        ItemStack itemStack = player.getHeldItem(hand);
        if (itemStack.getItem() instanceof ItemAir) {
            ComputerRelayBoxTileEntity tileEntity = (ComputerRelayBoxTileEntity) world.getTileEntity(pos);
            String entityID = tileEntity.getSourceID();
            player.sendMessage(new TextComponentString("Entity ID is: " + entityID));
            return true;
        } else if (itemStack.getItem() instanceof EntityParingToolItem) {
            if (itemStack.hasTagCompound()) {
                String entityID = itemStack.getTagCompound().getString("target");
                ComputerRelayBoxTileEntity tileEntity = (ComputerRelayBoxTileEntity) world.getTileEntity(pos);
                tileEntity.setSourceID(entityID);
                player.sendMessage(new TextComponentString("Entity ID set: " + entityID));
            } else {
                player.sendMessage(new TextComponentString("No entity ID in paring tool"));
            }
            return true;
        } else {
            return false;
        }
    }

}
