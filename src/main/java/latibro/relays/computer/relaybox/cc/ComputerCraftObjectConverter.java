package latibro.relays.computer.relaybox.cc;

import latibro.relays.computer.relaybox.common.ComputerObjectConverter;

public class ComputerCraftObjectConverter extends ComputerObjectConverter {

    @Override
    public ComputerCraftObjectWrapper wrapperObject(Object object) {
        return new ComputerCraftObjectWrapper(object);
    }

    @Override
    public boolean isWrappedObject(Object object) {
        return object instanceof ComputerCraftObjectWrapper;
    }
}
