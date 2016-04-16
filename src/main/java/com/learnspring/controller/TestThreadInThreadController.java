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

    /**
     * 顺序处理
     *
     * @param ids
     * @throws InterruptedException
     */
    @RequestMapping(value = "/notinthread", method = RequestMethod.GET)
    public
    @ResponseBody
    void threadnotinthread(@RequestParam(value = "ids") List<Integer> ids) throws InterruptedException {
        Long time1 = System.currentTimeMillis();
        for (Integer id : ids) {
            logger.info("--外部,id为:" + id);
            //模拟执行复杂操作,耗时1000毫秒
            Thread.sleep(1000);
            String s = notInThread("内部ID:" + id);
            System.out.println("返回结果为" + ":" + s);
        }
        Long time2 = System.currentTimeMillis();
        logger.info("消耗时间为:" + (time2 - time1));
    }

    public String notInThread(final String id) throws InterruptedException {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            logger.info("++内部,id为:" + id);
            //模拟执行复杂操作,耗时1000毫秒
            Thread.sleep(1000);
            s.append("执行结果" + id);
        }
        return s.toString();
    }

    /**
     * 多线程梳理
     *
     * @param ids
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping(value = "/inthread", method = RequestMethod.GET)
    public
    @ResponseBody
    void threadinthread(@RequestParam(value = "ids") List<Integer> ids) throws InterruptedException, ExecutionException {
        Long time1 = System.currentTimeMillis();
        List<Future<String>> results = new ArrayList();
        for (Integer id : ids) {
            Callable<String> callable = () -> {
                logger.info("--外部,id为:" + id);
                //模拟执行复杂操作,耗时1000毫秒
                Thread.sleep(1000);
                String s = inThread("内部ID:" + id);
                return s;
            };
            results.add(threadPool.submit(callable));
        }
        for (Future<String> future : results) {
            System.out.println("返回结果为" + ":" + future.get());
        }
        Long time2 = System.currentTimeMillis();
        logger.info("消耗时间为:" + (time2 - time1));
    }

    public String inThread(final String id) throws InterruptedException, ExecutionException {
        List<Future<String>> results = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Callable<String> callable = () -> {
                logger.info("++内部,id为:" + id);
                //模拟执行复杂操作,耗时1000毫秒
                Thread.sleep(1000);
                return "执行结果" + id;
            };
            results.add(threadPool.submit(callable));
        }
        StringBuilder s = new StringBuilder();
        for (Future<String> future : results) {
            s.append(future.get());
        }
        return s.toString();
    }

    private static final Log logger = LogFactory.getLog(TestThreadInThreadController.class);
    private ExecutorService threadPool = Executors.newFixedThreadPool(8, new NamedThreadFactory("trip-midas-deal"));

}