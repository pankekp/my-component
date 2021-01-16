package pers.pk.file.util;

import pers.pk.file.constant.FileUtilCharset;
import pers.pk.file.exception.FileUtilException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ReadUtil {

    public static List<String> readFileByLine(String filePath, FileUtilCharset fileUtilCharset) throws FileUtilException {

        File file = new File(filePath);
        List<String> result = new LinkedList<>();
        String line;

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, fileUtilCharset.getValue());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }

            return result;

        } catch (IOException e) {
            throw new FileUtilException(e.getMessage(), e);
        }
    }
}
