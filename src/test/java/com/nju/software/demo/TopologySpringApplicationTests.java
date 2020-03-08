package com.nju.software.demo;

import com.nju.software.Bean.User;
import com.nju.software.Dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TopologySpringApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        User u = new User();
        u.setId(u.getUUID());
        u.setPhone("11111");
        u.setUsername("哈哈");
        u.setCreatedAt(new Date().getTime());
        userDao.save(u);

    }

}
