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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gewei on 15/12/2.
 */
@Controller
@RequestMapping(value = "/threadtest", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestThreadInThreadController {

    @RequestMapping(value = "/notinthread", method = RequestMethod.GET)
    public
    @ResponseBody
    void threadnotinthread(@RequestParam(value = "ids") List<Integer> ids) throws InterruptedException {
        for (Integer id : ids) {
            logger.info("--外部,id为:" + id);
            Thread.sleep(1000);
            notInThread(id);
            Thread.sleep(1000);
        }
    }

    public void notInThread(Integer id) throws InterruptedException {
        Thread.sleep(1000);
        for (int i = 0; i < 6; i++) {
            logger.info("++内部,id为:" + id);
            Thread.sleep(1000);
        }
    }

    @RequestMapping(value = "/inthread", method = RequestMethod.GET)
    public
    @ResponseBody
    void threadinthread(@RequestParam(value = "ids") List<Integer> ids) throws InterruptedException, ExecutionException {
        List<Future<Object>> results = new ArrayList();
        for (Integer id : ids) {
            Callable<Object> callable = () -> {
                logger.info("--外部,id为:" + id);
                Thread.sleep(1000);
                inThread("内部ID:" + id);
                Thread.sleep(1000);
                return null;
            };
            results.add(threadPool.submit(callable));
        }
        for (Future<Object> future : results) {
            future.get();
        }
    }

    public void inThread(String id) throws InterruptedException, ExecutionException {
        Thread.sleep(1000);
        List<Future<String>> results = new ArrayList();
        for (int i = 0; i < 6; i++) {
            Callable<String> callable = () -> {
                logger.info("++内部,id为:" + id);
                Thread.sleep(1000);
                //                inInThread(id);
                return "++内部,id为:" + id;
            };
            results.add(threadPool.submit(callable));
        }
        for (Future<String> future : results) {
            future.get();
        }
    }

    public void inInThread(Integer id) throws InterruptedException, ExecutionException {
        Thread.sleep(1000);
        List<Future<Object>> results = new ArrayList();
        for (int i = 0; i < 6; i++) {
            Callable<Object> callable = () -> {
                logger.info("++内部的内部,id为:" + id);
                Thread.sleep(1000);
                return null;
            };
            results.add(threadPool.submit(callable));
        }
        for (Future<Object> future : results) {
            future.get();
        }
    }

    private static final Log logger = LogFactory.getLog(TestThreadInThreadController.class);
    private ExecutorService threadPool = Executors.newFixedThreadPool(2, new NamedThreadFactory("trip-midas-deal"));

}