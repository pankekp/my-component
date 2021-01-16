package pers.pk;

import pers.pk.file.constant.FileUtilCharset;
import pers.pk.file.exception.FileUtilException;
import pers.pk.file.util.ReadUtil;
import pers.pk.file.util.WriteUtil;

import java.util.LinkedList;
import java.util.List;

public class FileUtilTest {

    public static void main(String[] args) {

        List<String> test = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            test.add("测试");
        }

        String path = "/home/panke/Desktop/test";

        try {
            WriteUtil.writeFileByLine(path, test, FileUtilCharset.UTF_8);

            List<String> result = ReadUtil.readFileByLine(path, FileUtilCharset.UTF_8);

            for (String line : result) {
                System.out.println(line);
            }
        } catch (FileUtilException e) {
            System.out.println("error");
        }
    }
}