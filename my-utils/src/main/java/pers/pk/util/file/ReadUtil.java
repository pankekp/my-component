package pers.pk.util.file;

import pers.pk.enums.FileUtilCharset;
import pers.pk.exception.UtilException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public final class ReadUtil {

    private ReadUtil() {
    }

    public static List<String> readFileByLine(String filePath, FileUtilCharset fileUtilCharset) throws UtilException {

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
            throw new UtilException(e.getMessage(), e);
        }
    }
}
