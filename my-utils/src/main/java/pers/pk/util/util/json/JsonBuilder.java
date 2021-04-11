package pers.pk.util.util.json;

import com.google.common.base.Strings;

public class JsonBuilder {

    private StringBuilder stringBuilder;

    public JsonBuilder() {
        this.stringBuilder = new StringBuilder();
        stringBuilder.append("{");
    }

    public JsonBuilder appendKV(String key, String value) {

        if (Strings.isNullOrEmpty(key)) {
            return this;
        }

        if (stringBuilder.length() > 1) {
            stringBuilder.append(", ");
        }

        stringBuilder.append(formatKV(key, value));

        return this;
    }

    private String formatKV(String key, String value) {

        return "\"" + key + "\"" + ": " + "\"" + value + "\"";
    }

    @Override
    public String toString() {

        return stringBuilder.append("}").toString();
    }
}
