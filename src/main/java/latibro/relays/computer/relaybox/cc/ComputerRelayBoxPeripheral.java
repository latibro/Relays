package latibro.relays.computer.relaybox.cc;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import latibro.relays.RelaysMod;
import latibro.relays.computer.relaybox.oc.OCObjectConverter;
import latibro.relays.integration.devtest.DevTestImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComputerRelayBoxPeripheral implements IPeripheral {

    @Nonnull
    @Override
    public String getType() {
        return "computer_relay_box";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[]{
                "getApi",
                "cc"
        };
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - {}", methodIndex);
        Object object = new DevTestImpl();
        if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
            //TODO for some reason methods, found on both CC and OC, it seems the CC version has priority over OC methods when called from OC
            RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - OC");
            return new Object[]{OCObjectConverter.toOCObject(object)};
        } else {
            RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - CC");
            return new Object[]{new ComputerCraftObjectConverter().wrapperObject(object)};
        }
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

}
