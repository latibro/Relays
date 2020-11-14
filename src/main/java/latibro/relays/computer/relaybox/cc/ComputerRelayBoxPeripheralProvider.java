package latibro.relays.computer.relaybox.cc;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComputerRelayBoxPeripheralProvider implements IPeripheralProvider {

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull EnumFacing enumFacing) {
        if (world.getTileEntity(blockPos) instanceof ComputerRelayBoxTileEntity) {
            ComputerRelayBoxTileEntity computerRelayBox = (ComputerRelayBoxTileEntity) world.getTileEntity(blockPos);
            return new ComputerRelayBoxPeripheral(computerRelayBox);
        } else {
            return null;
        }
    }

}
