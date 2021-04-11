package pers.pk.util.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.pk.util.exception.UtilException;

import java.lang.reflect.Field;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T transStrToObj(String json, Class<T> valueType) throws UtilException {

        T value = null;

        try {
            value = objectMapper.readValue(json, valueType);
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
}
