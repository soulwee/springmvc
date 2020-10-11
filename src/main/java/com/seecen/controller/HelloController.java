package com.seecen.controller;

import com.seecen.entity.User;
import com.seecen.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;


    /*
    @RequestParam  required：是否必须。默认为 true, 表示请求参数中必须包含对应
    的参数，若不存在，将抛出异常*/
    @RequestMapping("index")
    public String index(@RequestParam("username")String username,@RequestParam(value = "password",required = false) String pass, Map map){
        //这里声明的username
        System.out.println(username +",mm:"+pass);
        System.out.println("hello mvc");
        map.put("username",username);
        return "index";
    }
    /*
    * @RequestMapping 的 value、method、params 及 heads
        分别表示请求 URL、请求方法、请求参数及请求头的映射条
        件，他们之间是与的关系，联合使用多个条件可让请求映射
        更加精确化
    */
    //@RequestMapping("in")// 相当于 mvc/in.do
    @RequestMapping(value="in",method = RequestMethod.POST,params = "userName")// 相当于 mvc/in.do
    public String index2(User user){
        //这里声明的username
        System.out.println(user.getUserName() +",mm:"+user.getPassword());
        System.out.println("hello mvc");
        return "index"; //视图为/index.jsp
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
