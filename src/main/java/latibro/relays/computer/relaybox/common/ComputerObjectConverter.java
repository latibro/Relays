package latibro.relays.computer.relaybox.common;

import latibro.relays.RelaysMod;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ComputerObjectConverter {

    public Object toComputerObject(Object object) {
        RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - {}", object);
        //TODO handle array/collection (maybe transform to Map as Lua doesn't have array)
        //TODO handle map (lua table)
        //TODO convert values of map/array/collection values
        if (object == null) {
            // Null - No need to do anything
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - null");
            return null;
        } else if (isWrappedObject(object)) {
            // Already wrapped
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapped");
            return object;
        } else if (object instanceof Boolean ||
                object instanceof String ||
                object instanceof Number
        ) {
            // Primitive - no need to wrap
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - primitive");
            return object;
        } else if (object instanceof Object[]) {
            // Array - convert each element
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - array");
            return Arrays.stream((Object[]) object).map(this::toComputerObject).toArray();
        } else if (object instanceof Collection) {
            // Collection - convert elements, and return as array
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - collection");
            return ((Collection) object).stream().map(this::toComputerObject).toArray();
        } else if (object instanceof Map) {
            // Map - convert both keys and elements
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - map");
            Map newMap = new HashMap();
            ((Map) object).forEach((k, v) -> newMap.put(toComputerObject(k), toComputerObject(v)));
            return newMap;
        } else {
            // Everyting else - Wrapping it
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapping");
            RelaysMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapping - type " + object.getClass().getName());
            return wrapperObject(object);
        }    }

    public Object fromComputerObject(Object object) {
        RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - {}", object);
        //TODO handled array/list/set
        //TODO handle map (maybe change some to array, as lua doesn't have array)
        //TODO convert values of map/array/list/set values
        if (object == null) {
            // Null - No need to do anything
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - null");
            return null;
        } else if (object instanceof ComputerObjectWrapper) {
            // Wrapped - Unwrapping
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - unwrapping");
            //TODO maybe recursive just ot make use its wasn't double wrapped
            return ((ComputerObjectWrapper) object).getObject();
        } else if (object instanceof Object[]) {
            // Array - Unwrapping elements
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - array");
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - array - type: {}", object.getClass().getName());
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - array - length: {}", ((Object[]) object).length);
            return Arrays.stream((Object[]) object).map(this::fromComputerObject).toArray();
        } else {
            // Everyting else - Passing it through
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - Pass-through");
            RelaysMod.logger.debug("ComputerObjectConverter.fromComputerObject - Pass-through - type: {}", object.getClass().getName());
            return object;
        }
    }

    public abstract ComputerObjectWrapper wrapperObject(Object object);

    public abstract boolean isWrappedObject(Object object);

}
