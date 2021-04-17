package pers.pk;

import pers.pk.util.constant.FileUtilCharset;
import pers.pk.util.exception.UtilException;
import pers.pk.util.util.file.WriteUtil;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    public static void main(String[] args) {

        String sourceFile = "/home/panke/source";
        String destFile = "/home/panke/result";

        List<String> list = new ArrayList<>();


        try {

            list.clear();
            list.add("444");
            list.add("555");
            list.add("666");

            WriteUtil.writeFileByLineAppend(destFile, list, FileUtilCharset.UTF_8, true);

        } catch (UtilException e) {
            e.printStackTrace();
        }

    }

}
