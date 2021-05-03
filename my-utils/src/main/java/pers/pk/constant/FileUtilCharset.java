package pers.pk.constant;

public enum FileUtilCharset {

    UTF_8("UTF-8"),
    GBK("GBK");

    private String value;

    FileUtilCharset(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
