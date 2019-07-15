package com.dao.setup;

import com.dao.setup.entity.User;
import com.dao.setup.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcedureDaoApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        System.out.println(userList);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
