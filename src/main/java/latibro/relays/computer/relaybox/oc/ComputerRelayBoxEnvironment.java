package latibro.relays.computer.relaybox.oc;

import latibro.relays.RelaysMod;
import latibro.relays.integration.devtest.DevTestImpl;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;

public class ComputerRelayBoxEnvironment extends AbstractManagedEnvironment implements NamedBlock, ManagedPeripheral {

    public ComputerRelayBoxEnvironment() {
        setNode(Network.newNode(this, Visibility.Network).withComponent(preferredName(), Visibility.Network).create());
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
                "oc"
        };
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        RelaysMod.logger.debug("ComputerRelayBoxEnvironment:invoke", method);
        return new Object[]{OCObjectConverter.toOCObject(new DevTestImpl())};
    }

    public void onConnect(Node node) {
        super.onConnect(node);
    }

    public void onDisconnect(Node node) {
        super.onDisconnect(node);
    }

}
