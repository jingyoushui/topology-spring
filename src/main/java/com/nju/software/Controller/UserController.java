package com.nju.software.Controller;

import com.nju.software.Bean.User;
import com.nju.software.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
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
    @RequestMapping(value = "/profile")
    public User profile(){
        User user = userService.findUserById("7f0c299301db4f70a507ff90d6ed81ba");//数据库中存在的一条数据
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

    @ResponseBody
    @RequestMapping(value = "/addUser")
    public Map<String,String > addUser(@RequestBody User user){
        user.setId(user.getUUID());
        String id = userService.save(user);
        System.out.println(user.toString());
        Map<String,String> sMap = new HashMap<>();
        sMap.put("id",id);
        return sMap;
    }
}
