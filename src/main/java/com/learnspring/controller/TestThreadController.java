package com.learnspring.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class TestThreadController {
    private static final Log logger = LogFactory.getLog(TestThreadController.class);
    private ExecutorService threadPool = Executors.newFixedThreadPool(4, new NamedThreadFactory("trip-midas-deal"));

    @RequestMapping(value = "/cost", method = RequestMethod.GET)
    public
    @ResponseBody
    void getProjectDetail() throws InterruptedException {
        Long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            Thread.sleep(100);
        }
        Long timeEnd = System.currentTimeMillis();
        System.out.println("消耗时间:" + (timeEnd - timeStart));
    }
}