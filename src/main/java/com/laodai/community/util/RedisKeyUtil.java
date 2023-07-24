package com.laodai.community.util;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE ="like:user";
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";//a关注了b a是b的粉丝（follower）b是a的目标（followee）
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER="user";
    //某个实体的赞
    //           键                      值：set集合 集合有统计数量的方法
    //like:entity:entityType:entityId -> set(userId)
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    //某个用户的赞
    //like:user:userId -> int
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT +userId;
    }

    //某个用户关注的实体   一定要体现用户和实体之间的关系
    //followee:userId:entityType -> zset(entityId,now)
    //        谁关注了   关注的实体    有序的集合     当前时间的整数作为存储
    public static String getFolloweeKey(int userId,int entityType){
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    //某个用户拥有的粉丝
    //follower:entityType:entityId -> zset(userId,now)
    public static String getFollowerKey(int entityType,int entityId){
        return PREFIX_FOLLOWER + SPLIT +entityType + SPLIT + entityId;
    }

    //登录验证
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    //登录的凭证
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }

    //用户
    public static String getUserKey(int userId){
        return PREFIX_USER + SPLIT + userId;
    }
}
