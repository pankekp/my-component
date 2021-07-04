package pers.pk.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.pk.exception.UtilException;

import java.lang.reflect.Field;
import java.util.Map;

public final class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {

        objectMapper = new ObjectMapper();
    }

    private JsonUtil() {
    }

    public static <T> T transStrToObj(String json, Class<T> valueType) throws UtilException {

        T value = null;

        try {
            value = objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new UtilException(e.getMessage(), e);
        }

        return value;
    }

    public static <T> T transStrToObj(String json, TypeReference<T> valueTypeRef) throws UtilException {

        T value = null;

        try {
            value = objectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new UtilException(e.getMessage(), e);
        }

        return value;
    }

    public static <T> String transSplitStrToJson(String str, String separator, Class<T> valueType) throws UtilException {

        JsonBuilder jsonBuilder = new JsonBuilder();

        Field[] fields = valueType.getDeclaredFields();
        String[] values = str.split(separator);

        if (fields.length != values.length) {
            throw new UtilException("the length of keys is not equal with the length of values", new IllegalArgumentException());
        }

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType() != String.class) {
                throw new UtilException("the method support String fields only", new IllegalArgumentException());
            }

            jsonBuilder.appendKV(fields[i].getName(), values[i]);
        }

        return jsonBuilder.toString();
    }

    public static String mapToJson(Map map) throws UtilException {

        String result = "";

        if (map == null || map.size() == 0) {
            return result;
        }

        try {
            result = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new UtilException(e.getMessage(), e);
        }

        return result;
    }
}
