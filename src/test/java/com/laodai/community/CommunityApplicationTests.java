package com.laodai.community;

import com.laodai.community.dao.AlphaDao;
import com.laodai.community.dao.Impl.AlphaDaoImpl;
import com.laodai.community.service.AlphaService;
import com.laodai.community.service.Impl.AlphaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Test
	public void contextLoads() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean("alphaDaoImpl",AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement(){
		AlphaServiceImpl alphaServiceImpl = applicationContext.getBean(AlphaServiceImpl.class);
		System.out.println(alphaServiceImpl);

		alphaServiceImpl = applicationContext.getBean(AlphaServiceImpl.class);
		System.out.println(alphaServiceImpl);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	//@Qualifier("alphaDaoImpl")//用另一个实现了
	private AlphaDao alphaDao;

	@Autowired
	private AlphaServiceImpl alphaServiceImpl;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired
	private AlphaDaoImpl alphaDaoImpl;

	@Test
	public void testDI(){
		System.out.println(alphaDao.select());
		System.out.println(alphaServiceImpl);
		System.out.println(simpleDateFormat.format(new Date()));
		System.out.println(alphaDaoImpl.select());
	}
}
