package latibro.relays.computer.relaybox.cc;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import latibro.relays.RelaysMod;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import latibro.relays.computer.relaybox.oc.OCObjectConverter;
import latibro.relays.integration.devtest.DevTestImpl;
import latibro.relays.integration.entiry.EntityControlImpl;
import latibro.relays.integration.rail.rollingstock.CabControlsImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComputerRelayBoxPeripheral implements IPeripheral {

    private final ComputerRelayBoxTileEntity computerRelayBox;

    public ComputerRelayBoxPeripheral(ComputerRelayBoxTileEntity computerRelayBox) {
        this.computerRelayBox = computerRelayBox;
    }

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
                "getSource",
                "getCabControl",
                "getEntityControl",
                "cc"
        };
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - {}", methodIndex);
        String method = getMethodNames()[methodIndex];
        RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - {}", method);

        Object object;
        if ("getApi".equals(method)) {
            object = new DevTestImpl();
        } else if ("getSource".equals(method)) {
            object = computerRelayBox.getSource();
        } else if ("getCabControl".equals(method)) {
            object = new CabControlsImpl(computerRelayBox);
        } else if ("getEntityControl".equals(method)) {
            object = new EntityControlImpl(computerRelayBox);
        } else {
            throw new LuaException("NoSuchMethodException");
        }

        if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
            //TODO for some reason methods, found on both CC and OC, it seems the CC version has priority over OC methods when called from OC
            RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - OC");
            return new Object[]{OCObjectConverter.toOCObject(object)};
        } else {
            RelaysMod.logger.debug("ComputerRelayBoxPeripheral:callMethod - CC");
            return new Object[]{new ComputerCraftObjectConverter().toComputerObject(object)};
        }
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

}