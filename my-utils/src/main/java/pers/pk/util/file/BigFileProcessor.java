package pers.pk.util.file;

import pers.pk.bean.BaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class BigFileProcessor implements Callable<BaseResult> {

    private static final List<String> END = new ArrayList<>();

    private final BlockingQueue<List<String>> blockingQueue;
    private final Consumer<List<String>> processor;

    public BigFileProcessor(BlockingQueue<List<String>> blockingDeque, Consumer<List<String>> processor) {
        this.blockingQueue = blockingDeque;
        this.processor = processor;
    }

    @Override
    public BaseResult call() throws Exception {

        BaseResult result = new BaseResult();
        result.setSuccess(true);

        List<String> dataGroup = blockingQueue.poll();

        if (dataGroup == null) {
            return result;
        }

        if (dataGroup.isEmpty()) {
            blockingQueue.add(END);
            return result;
        }

        try {
            processor.accept(dataGroup);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        dataGroup.clear();

        return result;
    }
}
