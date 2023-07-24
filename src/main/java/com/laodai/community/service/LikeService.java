package com.laodai.community.service;

import com.laodai.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    //点赞 谁点的赞 点赞的实体 点赞实体的一部分
    public void like(int userId,int entityType,int entityId,int entityUserId){
       /* String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);

        Boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
        //true 我已经点过赞了，取消赞 把userId在集合中删除
        if (isMember){
            redisTemplate.opsForSet().remove(entityLikeKey,userId);
        }else{
            redisTemplate.opsForSet().add(entityLikeKey,userId);
        }*/
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);//这里拼的是被赞的那个人，而like方法里的userId是点赞的那个人，是实体的作者
                boolean isMember = operations.opsForSet().isMember(entityLikeKey,userId);
                operations.multi();//开始事务

                if (isMember) {
                    operations.opsForSet().remove(entityLikeKey,userId);
                    operations.opsForValue().decrement(userLikeKey);
                }else {
                    operations.opsForSet().add(entityLikeKey,userId);
                    operations.opsForValue().increment(userLikeKey);
                }

                return operations.exec();//执行事务
            }
        });
    }

    //查村实体点赞的数量
    public long findEntityLikeCount(int entityType,int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    //查询某人对某实体点赞状态
    public int findEntityLikeStatus(int userId,int entityType,int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey,userId) ? 1 : 0;
    }
    //查询某个用户获得的赞
    public  int findUserLikeCount(int userId){
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count.intValue();
    }

}
