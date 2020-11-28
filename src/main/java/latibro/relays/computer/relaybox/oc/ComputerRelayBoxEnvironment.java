package latibro.relays.computer.relaybox.oc;

import latibro.relays.RelaysMod;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import latibro.relays.integration.devtest.DevTestImpl;
import latibro.relays.integration.entiry.EntityControlImpl;
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
                "getEntityControl",
                "oc"
        };
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        RelaysMod.logger.debug("ComputerRelayBoxEnvironment:invoke", method);
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
            throw new NoSuchMethodException();
        }

        return new Object[]{OCObjectConverter.toOCObject(object)};
    }

    public void onConnect(Node node) {
        super.onConnect(node);
    }

    public void onDisconnect(Node node) {
        super.onDisconnect(node);
    }

}
