package pers.pk.file.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.pk.file.exception.BaseException;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T transStrToObj(String json, Class<T> valueType) throws BaseException {

        T value = null;

        try {
            value = objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new BaseException(e.getMessage(), e);
        }

        return value;
    }

    public static <T> T transStrToObj(String json, TypeReference<T> valueTypeRef) throws BaseException {

        T value = null;

        try {
            value = objectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new BaseException(e.getMessage(), e);
        }

        return value;
    }
}
