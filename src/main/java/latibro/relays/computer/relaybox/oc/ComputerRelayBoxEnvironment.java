package latibro.relays.computer.relaybox.oc;

import latibro.relays.RelaysMod;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import latibro.relays.integration.devtest.DevTestImpl;
import latibro.relays.integration.rail.rollingstock.CabControlsImpl;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;

import java.lang.reflect.InvocationTargetException;

public class ComputerRelayBoxEnvironment extends AbstractManagedEnvironment implements NamedBlock, ManagedPeripheral {

    private final ComputerRelayBoxTileEntity computerRelayBox;

    public ComputerRelayBoxEnvironment(ComputerRelayBoxTileEntity computerRelayBox) {
        setNode(Network.newNode(this, Visibility.Network).withComponent(preferredName(), Visibility.Network).create());
        this.computerRelayBox = computerRelayBox;
    }

    @Override
    public String preferredName() {
        return "computer_relay_box";
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public String[] methods() {
        return new String[] {
                "getApi",
                "getSource",
                "getCabControl",
                "oc"
        };
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        RelaysMod.logger.debug("ComputerRelayBoxEnvironment:invoke", method);
        if ("getApi".equals(method)) {
            return new Object[]{OCObjectConverter.toOCObject(new DevTestImpl())};
        } else if ("getSource".equals(method)) {
            return new Object[]{OCObjectConverter.toOCObject(computerRelayBox.getSource())};
        } else if ("getCabControl".equals(method)) {
            return new Object[]{OCObjectConverter.toOCObject(new CabControlsImpl(computerRelayBox))};
        } else {
            throw new NoSuchMethodException();
        }
    }

    public void onConnect(Node node) {
        super.onConnect(node);
    }

    public void onDisconnect(Node node) {
        super.onDisconnect(node);
    }

}
