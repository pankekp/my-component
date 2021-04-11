package pers.pk;

import com.fasterxml.jackson.annotation.JsonProperty;
import pers.pk.util.constant.FileUtilCharset;
import pers.pk.util.exception.UtilException;
import pers.pk.util.util.file.ReadUtil;
import pers.pk.util.util.file.WriteUtil;
import pers.pk.util.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    public static void main(String[] args) {

        String sourceFile = "/home/panke/source";
        String destFile = "/home/panke/result";

        List<String> destList = new ArrayList<>();

        try {
            List<String> sourceList = ReadUtil.readFileByLine(sourceFile, FileUtilCharset.UTF_8);
            for (String line : sourceList) {
                destList.add(JsonUtil.transSplitStrToJson(line, ",", Obj.class));
            }
            WriteUtil.writeFileByLine(destFile, destList, FileUtilCharset.UTF_8);

            for (String line : destList) {
                Obj obj = JsonUtil.transStrToObj(line, Obj.class);
                System.out.println(obj.toString());
            }


        } catch (UtilException e) {
            System.err.println(e.getMsg());
            e.getException().printStackTrace();
        }

    }


    private static class Obj {

        @JsonProperty
        private String F_name;
        @JsonProperty
        private String F_age;
        @JsonProperty
        private String F_sex;

        @Override
        public String toString() {
            return "Obj{" +
                    "F_name='" + F_name + '\'' +
                    ", F_age='" + F_age + '\'' +
                    ", F_sex='" + F_sex + '\'' +
                    '}';
        }
    }
}
