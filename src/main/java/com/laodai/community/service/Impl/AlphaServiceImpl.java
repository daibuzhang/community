package com.laodai.community.service.Impl;

import com.laodai.community.dao.AlphaDao;
import com.laodai.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Service
//@Scope("prototype") bean的作用域，加上prototype就会每次都会创造一个新的bean，多例，一般都是用单例的，很少用多例
public class AlphaServiceImpl implements AlphaService {

    @Autowired
    private AlphaDao alphaDao;

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
}
