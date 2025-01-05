package com.ls.mp.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.mp.pojo.User;
import com.ls.mp.service.UserService;
import io.swagger.annotations.Api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private  UserService userService;
    @Test
    void testSaveOneByOne() {
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            userService.save(buildUser(i));
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    private User buildUser(int i) {
        User user = new User();
        user.setUsername("user_" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
//        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(user.getCreateTime());
        return user;
    }

    @Test
    void testSaveBatch() {
        long b = System.currentTimeMillis();
        List<User> list = new ArrayList<>(1000);
        for (int i = 1; i <= 100000; i++) {
                list.add(buildUser(i));
                if (i % 1000 == 0) {
                    userService.saveBatch(list);
                    list.clear();
                }
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }


    /**
     *分页查询你
     */
    @Test
    void testPageQuery() {
        int pageNo = 1;
        int pageSize = 10;
        // 构造分页对象
        Page<User> page = Page.of(pageNo, pageSize);
        // 排序参数, 通过OrderItem来指定 构造排序
        page.addOrder(OrderItem.asc("balance"));

        page = userService.page(page);
        //获取总条数
        long total = page.getTotal();
        System.out.println("total = " + total);
        //获取总页数
        long pages = page.getPages();
        System.out.println("pages = " + pages);
        //获取当前页码
        long size = page.getSize();
        System.out.println("size = " + size);
        //获取当前页数据
        long current = page.getCurrent();
        System.out.println("current = " + current);
        //获取分页数据
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        

        

    }
}