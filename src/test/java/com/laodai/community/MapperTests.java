package com.laodai.community;


import com.laodai.community.dao.DiscussPostMapper;
import com.laodai.community.dao.UserMapper;
import com.laodai.community.entity.DiscussPost;
import com.laodai.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setType(0);
        user.setStatus(1);
        user.setActivationCode("null");
        user.setHeaderUrl("http://www.nowcoder.com/110");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser(){
        int rows = userMapper.updateStatus(150, 0);
        System.out.println(rows);

        rows = userMapper.updateHeader(150,"http");
        System.out.println(rows);

        rows = userMapper.updatePassword(150,"111111");
        System.out.println(rows);
    }

    @Autowired
    private DiscussPostMapper DiscussPostMapper;
    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = DiscussPostMapper.selectDiscussPost(149, 0, 10);
        list.forEach(System.out::println);

        int rows = DiscussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }


}
