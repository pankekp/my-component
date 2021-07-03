package pers.pk;

import pers.pk.enums.FileUtilCharset;
import pers.pk.util.file.BigFileProcessor;
import pers.pk.util.file.BigFileReader;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class BigFileTest {

    private static final String filePath = "";

    private static Consumer<List<String>> processor;

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<List<String>> blockingQueue = new ArrayBlockingQueue<>(1024);

        ThreadPoolExecutor pools = getThreadPool();

        pools.submit(new BigFileReader(blockingQueue, filePath, FileUtilCharset.UTF_8, 1024));

        while (true) {

            if (blockingQueue.isEmpty()) {
                TimeUnit.SECONDS.sleep(1);
            }

            List<String> dataGroup = blockingQueue.peek();

            if (dataGroup != null && dataGroup.isEmpty()) {
                break;
            }

            pools.submit(new BigFileProcessor(blockingQueue, processor));
        }

        pools.shutdown();
    }

    private static ThreadPoolExecutor getThreadPool() {
        return new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
