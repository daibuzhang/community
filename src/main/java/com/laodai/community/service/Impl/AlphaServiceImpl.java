package com.laodai.community.service.Impl;

import com.laodai.community.dao.AlphaDao;
import com.laodai.community.dao.DiscussPostMapper;
import com.laodai.community.dao.UserMapper;
import com.laodai.community.entity.DiscussPost;
import com.laodai.community.entity.User;
import com.laodai.community.service.AlphaService;
import com.laodai.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;

@Service
//@Scope("prototype") bean的作用域，加上prototype就会每次都会创造一个新的bean，多例，一般都是用单例的，很少用多例
public class AlphaServiceImpl implements AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    //构造器
    public AlphaServiceImpl() {
        System.out.println("实例化AlphaServiceImpl");
    }


    @PostConstruct//让容器在合适的地方自动的调这个方法,在构造器之后调用
    public void initAlphaService(){
        System.out.println("初始化AlphaServiceImpl");
    }


    @PreDestroy//在销毁对象之前调用它
    public void destroy(){
        System.out.println("销毁AlphaServiceImpl");
    }

    public String find(){
        return alphaDao.select();
    }

    /**
     * REQUIRED: 支持当前事务（外部事务），如果不存在，就创造新事务
     * REQUIRES_NEW：创建一个新事务，斌且暂停当前事务
     * NESTED：如果当前存在事务（外部事务），则嵌套在该事务中执行（独立的提交和回滚），否则就会和REQUIRED一样
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Object save1(){
        //新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        //新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("hello");
        post.setContent("新人报到");
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);

        Integer.valueOf("abc");

        return "ok";
    }

    public Object save2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            //回调方法
            @Override
            public Object doInTransaction(TransactionStatus status) {

                //新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0,5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("beta@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/98t.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);
                //新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("hello!");
                post.setContent("新人报到");
                post.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(post);

                Integer.valueOf("abc");

                return "ok";
            }
        });
    }
}
