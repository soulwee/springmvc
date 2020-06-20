package com.seecen.controller;

import com.seecen.entity.User;
import com.seecen.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("index.do")
    public String index(String username,@RequestParam("password") String pass, Map map){
        //这里声明的username
        System.out.println(username +"mm:"+pass);
        System.out.println("hello mvc");
        map.put("username",username);
        return "index";
    }
    @RequestMapping("in.do")
    public String index2(User user){
        //这里声明的username
        System.out.println(user.getUserName() +"mm:"+user.getPassword());
        System.out.println("hello mvc");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("tomcat..");
        return hello;
    }

    //  /WEB-INF/views/success.jsp
    @RequestMapping("/suc")
    public String success(){
        return "success";
    }
}
