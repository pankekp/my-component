package pers.pk.file.util;

import pers.pk.file.constant.FileUtilCharset;
import pers.pk.file.exception.FileUtilException;

import java.io.*;
import java.util.List;

public class WriteUtil {

    public static void writeFileByLine(String filePath, List<String> contents, FileUtilCharset fileUtilCharset) throws FileUtilException {

        File file = new File(filePath);

        try {

            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, fileUtilCharset.getValue());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (String line : contents) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();

        } catch (IOException e) {
            throw new FileUtilException(e.getMessage(), e);
        }
    }
}
