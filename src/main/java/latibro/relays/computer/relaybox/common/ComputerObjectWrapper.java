package latibro.relays.computer.relaybox.common;

import latibro.relays.RelaysMod;

import java.lang.reflect.Method;

public abstract class ComputerObjectWrapper {

    protected final Object wrappedObject;

    protected ComputerObjectWrapper(Object object) {
        RelaysMod.logger.debug("ComputerObjectWrapper:constructor - {}", object);
        this.wrappedObject = object;
    }

    public Object getObject() {
        return wrappedObject;
    }

    protected Method[] getMethodList() {
        return wrappedObject.getClass().getMethods();
    }


    protected Object invokeMethod(Method method, Object[] arguments) throws Exception {
        return method.invoke(wrappedObject, arguments);
    }

}
