package latibro.relays.computer.relaybox.oc;

import latibro.relays.RelaysMod;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.prefab.AbstractValue;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OCObjectWrapper extends AbstractValue implements ManagedPeripheral {

    private final Object wrappedObject;

    public OCObjectWrapper(Object object) {
        RelaysMod.logger.debug("OCObjectWrapper:constructor", object);
        this.wrappedObject = object;
    }

    public Object getObject() {
        return wrappedObject;
    }

    @Override
    public Object apply(Context context, Arguments arguments) {
        RelaysMod.logger.debug("OCObjectWrapper.apply");
        RelaysMod.logger.debug("OCObjectWrapper.apply - arguments", arguments);
        RelaysMod.logger.debug("OCObjectWrapper.apply - arguments.count", arguments.count());
        RelaysMod.logger.debug("OCObjectWrapper.apply - arguments[0]",  arguments.checkAny(0));
        return super.apply(context, arguments);
    }

    @Override
    public void unapply(Context context, Arguments arguments) {
        RelaysMod.logger.debug("OCObjectWrapper.unapply");
        RelaysMod.logger.debug("OCObjectWrapper.unapply - arguments", arguments);
        RelaysMod.logger.debug("OCObjectWrapper.unapply - arguments.count", arguments.count());
        RelaysMod.logger.debug("OCObjectWrapper.unapply - arguments[0]", arguments.checkAny(0));
        super.unapply(context, arguments);
    }

    @Override
    public Object[] call(Context context, Arguments arguments) {
        RelaysMod.logger.debug("OCObjectWrapper.call");
        RelaysMod.logger.debug("OCObjectWrapper.call - arguments : ", arguments);
        RelaysMod.logger.debug("OCObjectWrapper.call - arguments.count", arguments.count());
        RelaysMod.logger.debug("OCObjectWrapper.call - arguments[0]", arguments.checkAny(0));
        return super.call(context, arguments);
    }

    @Override
    public void dispose(Context context) {
        RelaysMod.logger.debug("OCObjectWrapper.dispose");
        super.dispose(context);
    }

    @Override
    public void load(NBTTagCompound nbt) {
        RelaysMod.logger.debug("OCObjectWrapper.load");
        super.load(nbt);
    }

    @Override
    public void save(NBTTagCompound nbt) {
        RelaysMod.logger.debug("OCObjectWrapper.save");
        super.save(nbt);
    }

    @Override
    public String[] methods() {
        //TODO maybe check for dublicates. Or maybe just distinct list and let invoke do the check based on arguments
        RelaysMod.logger.debug("OCObjectWrapper.methods");
        Method[] methods = wrappedObject.getClass().getMethods();
        return Arrays.stream(methods).map(Method::getName).toArray(String[]::new);
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        RelaysMod.logger.debug("OCObjectWrapper method called: " + method);
        //TODO make sure correct method is found without parameters to getMethod, or maybe pass the augments
        //TODO check if multiple methods match
        //TODO wrap return type if not "native" (li.cil.oc.api.driver.Converter to li.cil.oc.api.machine.Value)
        //TODO unwarp parameters if wrapped (reverse of li.cil.oc.api.driver.Converter)
        try {
            Method[] methods = wrappedObject.getClass().getMethods();
            Method[] matchingMethods = Arrays.stream(methods).filter(m -> m.getName().equals(method)).toArray(Method[]::new);
            //TODO check if multiple methods found
            Method methodObject = matchingMethods[0];
            RelaysMod.logger.debug("OCObjectWrapper.invoke - methodObject: " + methodObject);
            RelaysMod.logger.debug("OCObjectWrapper.invoke - obj: " + wrappedObject);

            RelaysMod.logger.debug("Arguments: count " + arguments.count());
            Arrays.stream(arguments.getClass().getInterfaces()).map(Class::getName).forEach(System.out::println);
            List list = new ArrayList();
            for (int i=0; i<arguments.count(); i++) {
                RelaysMod.logger.debug("Arguments: i: {}", i);
                Object x = arguments.checkAny(i);
                RelaysMod.logger.debug("Arguments: {}",  x);
                RelaysMod.logger.debug("Arguments: type: {}", x.getClass().getName());
                //RelaysMod.logger.debug("Arguments: type " + Arrays.stream(x.getClass().getMethods()).map(Method::getName).toArray(String[]::new));
                //Arrays.stream(x.getClass().getMethods()).map(Method::getName).forEach(System.out::println);
                RelaysMod.logger.debug("Arguments: interfaces:");
                Arrays.stream(x.getClass().getInterfaces()).map(Class::getName).forEach(System.out::println);
                RelaysMod.logger.debug("Arguments: classes:");
                Arrays.stream(x.getClass().getClasses()).map(Class::getName).forEach(System.out::println);
                RelaysMod.logger.debug("Arguments: declared classes:");
                Arrays.stream(x.getClass().getDeclaredClasses()).map(Class::getName).forEach(System.out::println);
                //RelaysMod.logger.debug("Arguments: package: " + x.getClass().getPackage().getName());
                //RelaysMod.logger.debug("Arguments: is LuaValueProxy: " + (x instanceof li.cil.repack.com.naef.jnlua.LuaValueProxy));
                //RelaysMod.logger.debug("Arguments: is AbstractTableList: " + (x instanceof li.cil.repack.com.naef.jnlua.util.AbstractTableList));
                list.add(arguments.checkAny(i));
            }

            //li.cil.repack.com.naef.jnlua.DefaultConverter;

            //List list = Lists.newArrayList(arguments.iterator());
            //RelaysMod.logger.debug("Arguments: list " + list);
            RelaysMod.logger.debug("Arguments: list size" + list.size());

            //Object[] args = (Object[]) OCObjectConverter.fromOCObject(arguments.toArray());
            Object[] args = (Object[]) OCObjectConverter.fromOCObject(list.toArray());
            RelaysMod.logger.debug("OCObjectWrapper.invoke - args: " + args);
            RelaysMod.logger.debug("OCObjectWrapper.invoke - args.lenght: " + args.length);
            Object ocResult = methodObject.invoke(wrappedObject, args);
            //RelaysMod.logger.debug("OCObjectWrapper.invoke - ocResult: " + ocResult);
            RelaysMod.logger.debug("OCObjectWrapper.invoke - ocResult type: " + (ocResult != null ? ocResult.getClass().getName() : null));
            Object result = OCObjectConverter.toOCObject(ocResult);
            //RelaysMod.logger.debug("OCObjectWrapper.invoke - result: " + result);
            RelaysMod.logger.debug("OCObjectWrapper.invoke - result type: " + (result != null ? result.getClass().getName() : null));
            return new Object[] {result};
        } catch (Exception e) {
            RelaysMod.logger.debug("OCObjectWrapper.invoke - exception: " + e.getMessage());
            e.printStackTrace();
            throw new Exception(e.getClass().getName() + ": " + e.getMessage(), e);
        }
    }

}
