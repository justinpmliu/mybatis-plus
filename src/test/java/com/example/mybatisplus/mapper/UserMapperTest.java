package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("Kitty");
        user.setAge(7);
        user.setEmail("kitty@nomail.com");
        userMapper.insert(user);

        assertEquals(6L, user.getId().longValue());

        List<User> userList = userMapper.selectList(null);
        assertEquals(6, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void testLogicalDelete() {
        userMapper.deleteById(1L);
        List<User> userList = userMapper.selectList(null);
        assertEquals(4, userList.size());
        userList.forEach(System.out::println);

        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getId, 1L);
        List<User> users = userMapper.selectList(queryWrapper);
        assertTrue(users.isEmpty());
    }
}