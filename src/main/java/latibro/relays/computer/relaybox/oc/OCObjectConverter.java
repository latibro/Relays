package latibro.relays.computer.relaybox.oc;

import latibro.relays.RelaysMod;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OCObjectConverter {

    public static Object toOCObject(Object obj) {
        RelaysMod.logger.debug("OCObjectConverter:toOCObject", obj);
        //TODO handle array/collection (maybe transform to Map as Lua doesn't have array)
        //TODO handle map (lua table)
        //TODO convert values of map/array/collection values
        if (obj == null) {
            // Null - No need to do anything
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - null");
            return null;
        } else if (obj instanceof OCObjectWrapper) {
            // Already wrapped
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - wrapped");
            return obj;
        } else if (obj instanceof Boolean ||
                obj instanceof String ||
                obj instanceof Number
        ) {
            // Primitive - no need to wrap
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - primitive");
            return obj;
        } else if (obj instanceof Object[]) {
            // Array - wrapping elements
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - array");
            return Arrays.stream((Object[]) obj).map(OCObjectConverter::toOCObject).toArray();
        } else if (obj instanceof Collection) {
            // Collection - wrapping elements into array
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - collection");
            return ((Collection) obj).stream().map(OCObjectConverter::toOCObject).toArray();
        } else if (obj instanceof Map) {
            // Map - wrapping keys and elements
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - map");
            Map newMap = new HashMap();
            ((Map) obj).forEach((k, v) -> newMap.put(OCObjectConverter.toOCObject(k), OCObjectConverter.toOCObject(v)));
            return newMap;
        } else {
            // Everyting else - Wrapping it
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - wrapping");
            RelaysMod.logger.debug("OCObjectConverter.toOCObject - wrapping - type " + obj.getClass().getName());
            return new OCObjectWrapper(obj);
        }
    }

    public static Object fromOCObject(Object obj) {
        RelaysMod.logger.debug("OCObjectConverter:toOCObject", obj);
        //TODO handled array/list/set
        //TODO handle map (maybe change some to array, as lua doesn't have array)
        //TODO convert values of map/array/list/set values
        if (obj == null) {
            // Null - No need to do anything
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - null");
            return null;
        } else if (obj instanceof OCObjectWrapper) {
            // Wrapped - Unwrapping
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - unwrapping");
            //TODO maybe recursive just ot make use its wasn't double wrapped
            return ((OCObjectWrapper) obj).getObject();
        } else if (obj instanceof Object[]) {
            // Array - Unwrapping elements
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - array");
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - array - type : " + obj.getClass().getName());
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - array - length : " + ((Object[]) obj).length);
            //Arrays.stream((Object[]) obj).forEach(o -> System.out.print("xxxx: " + o));
            return Arrays.stream((Object[]) obj).map(OCObjectConverter::fromOCObject).toArray();
        } else {
            // Everyting else - Passing it through
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - Pass-through");
            RelaysMod.logger.debug("OCObjectConverter.fromOCObject - Pass-through - type " + obj.getClass().getName());
            return obj;
        }
    }

}
