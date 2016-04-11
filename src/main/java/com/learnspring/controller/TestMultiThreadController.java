package com.learnspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gewei on 15/12/2.
 */
@Controller
@RequestMapping(value = "/threadtest", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestMultiThreadController {

    @RequestMapping(value = "/cost/single", method = RequestMethod.GET)
    public
    @ResponseBody
    void singleThread() throws InterruptedException {
        Long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(100);
        }
        Long timeEnd = System.currentTimeMillis();
        System.out.println("消耗时间:" + (timeEnd - timeStart));
    }

    @RequestMapping(value = "/cost/multi", method = RequestMethod.GET)
    public
    @ResponseBody
    void multiThread() throws InterruptedException, ExecutionException {
        Long timeStart = System.currentTimeMillis();
        List<Future<Integer>> results = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Callable<Integer> callable = () -> {
                Thread.sleep(100);
                return 1;
            };
            results.add(threadPool.submit(callable));
        }
        for (Future<Integer> future : results) {
            future.get();
        }
        Long timeEnd = System.currentTimeMillis();
        System.out.println("消耗时间:" + (timeEnd - timeStart));
    }

    private static final Log logger = LogFactory.getLog(TestMultiThreadController.class);
    private ExecutorService threadPool = Executors.newFixedThreadPool(10, new NamedThreadFactory("trip-midas-deal"));

}