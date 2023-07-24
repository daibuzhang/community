package com.laodai.community.dao.elasticsearch;


import com.laodai.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository                                                                       //实体类中的主键是什么类型
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {

}
