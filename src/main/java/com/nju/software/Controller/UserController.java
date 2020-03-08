package com.nju.software.Controller;

import com.nju.software.Bean.User;
import com.nju.software.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/")
    public String  index(Model model){
        return "index";
    }

    //测试首次前端的profile请求
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/user/profile")
    public User profile(){
        User user = userService.findUserById("39f8df817cea444ba880cd5fef2ec2f5");//数据库中存在的一条数据
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser")
    public User findUserById(String id){
        User user = userService.findUserById(id);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/getId")
    public Map<String,Object> getUser(){
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id","1");
        return sMap;
    }


}
