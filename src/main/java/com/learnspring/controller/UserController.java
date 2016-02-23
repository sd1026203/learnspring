package com.learnspring.controller;

import com.learnspring.bean.User;
import com.learnspring.biz.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gewei on 16/2/3.
 */
@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class UserController {
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public @ResponseBody String getDealDetail() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        String u = userBiz.getUser(user);
        return u;
    }
}
