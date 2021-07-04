package pers.pk.util.format;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

public final class KVFormatUtil {


    public static Map<String, Object> equalSign2Map(String input) {

        Map<String, Object> result = new HashMap<>();

        if (Strings.isNullOrEmpty(input)) {
            return result;
        }

        input = input.replace(" ", "");

        int strSize = input.length();
        int i = 0;

        StringBuilder currentKey = new StringBuilder();
        StringBuilder currentValue = new StringBuilder();
        StringBuilder currentSub = new StringBuilder();

        boolean isValue = false;
        boolean isSub = false;

        while (i <= strSize - 1) {

            char currentChar = input.charAt(i);

            if (isValue) {
                if (currentChar == ',') {
                    result.put(currentKey.toString(), currentValue.toString());
                    currentKey = new StringBuilder();
                    currentValue = new StringBuilder();
                    isValue = false;
                } else {
                    currentValue.append(currentChar);
                    if (i == strSize - 1) {
                        result.put(currentKey.toString(), currentValue.toString());
                    }
                }
            } else if (isSub) {
                if (currentChar == '}') {
                    result.put(currentKey.toString(), equalSign2Map(currentSub.toString()));
                    currentKey = new StringBuilder();
                    currentSub = new StringBuilder();
                    isSub = false;
                    i++;
                } else {
                    currentSub.append(currentChar);
                }
            } else if (currentChar != '=') {
                currentKey.append(currentChar);
            } else if (input.charAt(i) == '=') {
                // i+1" is to avoid that the equal sign is in last position
                // e.g. a=xxx.b=
                if (i + 1 <= strSize - 1 && input.charAt(i + 1) == '{') {
                    isSub = true;
                    i++;
                } else {
                    isValue = true;
                }
            }

            i++;
        }

        return result;
    }

}
