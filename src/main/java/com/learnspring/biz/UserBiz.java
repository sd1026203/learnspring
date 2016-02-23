package com.learnspring.biz;

import com.learnspring.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by gewei on 16/2/3.
 */
@Service
public class UserBiz {
    public UserBiz() {
        System.out.println(22222222);
    }
    public String getUser(User user) {
        return user.toString();
    }
}
