package latibro.relays.computer.relaybox.cc;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.ILuaObject;
import dan200.computercraft.api.lua.LuaException;
import latibro.relays.RelaysMod;
import latibro.relays.computer.relaybox.common.ComputerObjectWrapper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class ComputerCraftObjectWrapper extends ComputerObjectWrapper implements ILuaObject {

    public ComputerCraftObjectWrapper(Object object) {
        super(object);
        RelaysMod.logger.debug("ComputerCraftObjectWrapper:constructor - {}", object);
    }

    @Override
    public String[] getMethodNames() {
        //TODO maybe check for dublicates. Or maybe just distinct list and let invoke do the check based on arguments
        RelaysMod.logger.debug("OCObjectWrapper.methods");
        Method[] methods = wrappedObject.getClass().getMethods();
        return Arrays.stream(methods).map(Method::getName).toArray(String[]::new);
    }

    @Override
    public Object[] callMethod(ILuaContext context, int methodIndex, Object[] arguments) throws LuaException, InterruptedException {
        RelaysMod.logger.debug("OCObjectWrapper method called - number: {}", methodIndex);
        String method = getMethodNames()[methodIndex];
        RelaysMod.logger.debug("OCObjectWrapper method called - {}", method);
        //TODO make sure correct method is found without parameters to getMethod, or maybe pass the augments
        //TODO check if multiple methods match
        //TODO wrap return type if not "native"
        //TODO unwarp parameters if wrapped
        try {
            Method[] methods = wrappedObject.getClass().getMethods();
            Stream<Method> matchingMethods = Arrays.stream(methods).filter(m -> m.getName().equals(method));
            //TODO check if multiple methods found
            Method methodObject = matchingMethods.findFirst().get();
            RelaysMod.logger.debug("CCObjectWrapper.callMethod - methodObject: {}", methodObject);
            RelaysMod.logger.debug("CCObjectWrapper.callMethod - obj: {}", wrappedObject);

            RelaysMod.logger.debug("Arguments: count " + arguments.length);

            Object[] args = (Object[]) new ComputerCraftObjectConverter().toComputerObject(arguments);
            RelaysMod.logger.debug("CCObjectWrapper.callMethod - args: {}", args);

            Object ocResult = methodObject.invoke(wrappedObject, args);
            RelaysMod.logger.debug("OCObjectWrapper.invoke - ocResult: {}", ocResult);

            Object result = new ComputerCraftObjectConverter().toComputerObject(ocResult);
            RelaysMod.logger.debug("CCObjectWrapper.callMethod - result: {}", result);

            return new Object[] {result};
        } catch (Exception e) {
            RelaysMod.logger.debug("CCObjectWrapper.callMethod - exception: " + e.getMessage());
            e.printStackTrace();
            throw new LuaException(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
