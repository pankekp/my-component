package pers.pk.util.file;

import pers.pk.constant.FileUtilCharset;
import pers.pk.exception.UtilException;

import java.io.*;
import java.util.List;

public final class WriteUtil {

    private WriteUtil(){
    }

    public static void writeFileByLine(String filePath, List<String> contents, FileUtilCharset fileUtilCharset) throws UtilException {

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
            throw new UtilException(e.getMessage(), e);
        }
    }

    public static void writeFileByLineAppend(String filePath, List<String> contents, FileUtilCharset fileUtilCharset, boolean isAppend) throws UtilException {

        File file = new File(filePath);

        try {

            if (!file.exists()) {
                throw new UtilException("the file is not be created", new IllegalArgumentException());
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, fileUtilCharset.getValue());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (String line : contents) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();

        } catch (IOException e) {
            throw new UtilException(e.getMessage(), e);
        }
    }
}
