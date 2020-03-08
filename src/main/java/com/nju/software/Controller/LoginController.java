package com.nju.software.Controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.nju.software.Bean.User;
import com.nju.software.Token.TokenService;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/05/下午3:24
 * @Description:
 */
@RestController
//@RequestMapping("api")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    //登录

    @CrossOrigin
    @PostMapping("/login")
    public Object login(@RequestBody JSONObject jsonObject1) throws JSONException {
        String phone = jsonObject1.get("phone").toString();
        String password = jsonObject1.get("password").toString();
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findUserByPhone(phone);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(password)){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
