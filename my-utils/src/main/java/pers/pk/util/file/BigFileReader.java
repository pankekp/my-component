package pers.pk.util.file;

import pers.pk.bean.BaseResult;
import pers.pk.enums.FileUtilCharset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class BigFileReader implements Callable<BaseResult> {

    private static final int DATA_GROUP_SIZE = 1024;

    private static final List<String> END = new ArrayList<>();

    private final BlockingQueue<List<String>> blockingQueue;
    private final String filePath;
    private final FileUtilCharset fileUtilCharset;
    private final int queueThreshold;

    public BigFileReader(BlockingQueue<List<String>> blockingDeque, String filePath, FileUtilCharset fileUtilCharset, int queueThreshold) {
        this.blockingQueue = blockingDeque;
        this.filePath = filePath;
        this.fileUtilCharset = fileUtilCharset;
        this.queueThreshold = queueThreshold;
    }

    @Override
    public BaseResult call() throws Exception {

        BaseResult result = new BaseResult();
        result.setSuccess(true);

        File file = new File(filePath);
        String line;
        List<String> dataGroup = new ArrayList<>(DATA_GROUP_SIZE);

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, fileUtilCharset.getValue());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {

                if (dataGroup.size() < DATA_GROUP_SIZE) {
                    dataGroup.add(line);
                } else {
                    addDataToQueue(dataGroup);
                }
            }

            addDataToQueue(dataGroup);

            addDataToQueue(END);

        } catch (IOException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    private void addDataToQueue(List<String> dataGroup) throws InterruptedException {

        while (blockingQueue.size() >= queueThreshold) {
            TimeUnit.SECONDS.sleep(1);
        }

        blockingQueue.add(dataGroup);
    }
}
