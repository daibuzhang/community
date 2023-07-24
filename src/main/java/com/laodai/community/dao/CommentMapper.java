package com.laodai.community.dao;


import com.laodai.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    //根据实体来查询，查询的评论的评论，还是帖子的评论 还是课程的评论
    List<Comment>  selectCommentsByEntity(int entityType,int entityId,int offset,int limit);

    //查询数据的条数
    int selectCountByEntity(int entityType,int entityId);

    //增加评论
    int insertComment(Comment comment);

    //根据id查comment
    Comment selectCommentById(int id);
}
