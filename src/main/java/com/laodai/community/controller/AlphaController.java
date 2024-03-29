package com.laodai.community.controller;

import com.laodai.community.service.Impl.AlphaServiceImpl;
import com.laodai.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaServiceImpl alphaServiceImpl;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring World";
    }

    @RequestMapping("/data")
    @ResponseBody
    private String getData() {
        return alphaServiceImpl.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());//请求的方法
        System.out.println(request.getServletPath());//请求的路径
        Enumeration<String> enumeration = request.getHeaderNames();//获取所有请求行的key
        //针对上面这个迭代器，需要通过while进行遍历
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();//请求行的名字
            String value = request.getHeader(name);//name有对应的value
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>LiPu网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求 默认的请求就是get请求

    ///students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {

        System.out.println(current);
        System.out.println(limit);
        return "some students";

    }

    // /student/123
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String save(String name, Integer age){
        //声明的参数的名字和你表单中的名字一致，会自动的传过来
            System.out.println(name);
            System.out.println(age);
            return "success";
        }

        //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age","200");
        return "/demo/view";
    }

    //响应JSON数据(异步请求中，当前网页不刷新，但已经悄悄的访问了服务器)
    //java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","123");
        emp.put("salary","8000.00");
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","123");
        emp.put("salary","8000.00");
        list.add(emp);
        emp.put("name","李四");
        emp.put("age","23");
        emp.put("salary","5000.00");
        list.add(emp);
        emp.put("name","王五");
        emp.put("age","44");
        emp.put("salary","6000.00");
        list.add(emp);
        return list;
    }

    //cookie示例
    @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的的范围
        cookie.setPath("/community/alpha");
        //设置cookie生存时间
        cookie.setMaxAge(60 *10);
        //发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get" ,method= RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    //session示例
    @RequestMapping(path = "/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","Test");
        return "set Session";
    }

    @RequestMapping(path = "/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get Session";
    }

    //ajax示例
    @RequestMapping(path = "/ajax",method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0,"操作成功");
    }
}

