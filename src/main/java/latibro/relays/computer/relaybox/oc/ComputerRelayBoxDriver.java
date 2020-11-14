package latibro.relays.computer.relaybox.oc;

import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ComputerRelayBoxDriver extends DriverSidedTileEntity {

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos blockPos, EnumFacing enumFacing) {
        if (worksWith(world, blockPos, enumFacing)) {
            return new ComputerRelayBoxEnvironment();
        } else {
            return null;
        }
    }

    @Override
    public Class<?> getTileEntityClass() {
        return ComputerRelayBoxTileEntity.class;
    }

}
