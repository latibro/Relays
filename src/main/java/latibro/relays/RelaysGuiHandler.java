package latibro.relays;

import latibro.relays.computer.relaybox.ComputerRelayBoxContainer;
import latibro.relays.computer.relaybox.ComputerRelayBoxGui;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class RelaysGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof ComputerRelayBoxTileEntity) {
            return new ComputerRelayBoxContainer();
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof ComputerRelayBoxTileEntity) {
            return new ComputerRelayBoxGui(new ComputerRelayBoxContainer());
        }
        return null;
    }

}
