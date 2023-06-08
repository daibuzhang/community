package com.laodai.community.dao.Impl;


import com.laodai.community.dao.AlphaDao;
import org.springframework.stereotype.Repository;

@Repository//默认的bean名字是类名的小驼峰，也可以另外设别名@Repository("alpha")
public class AlphaDaoImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
