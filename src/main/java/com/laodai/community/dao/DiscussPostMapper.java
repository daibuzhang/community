package com.laodai.community.dao;

import com.laodai.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiscussPostMapper {


    List<DiscussPost> selectDiscussPost(int userId,int offset,int limit);

    //即使一个参数，如果用作判断条件也要使用@Param注解 动态sql if语句
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id,int commentCount);
}
