package com.nju.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.Topologie;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.TopologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TopologieController {

    @Autowired
    public TopologieService topologieService;

//    @CrossOrigin
//    @ResponseBody
//    @RequestMapping(value = "/topology")
//    public Topologie findTopologieById(String sid){
//        long id = Long.parseLong("1583321392333");
//        return topologieService.findTopologieById(id);
//    }


    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/user/topology")
    public Map<String,Object> save(@RequestBody JSONObject jsonObject, HttpServletRequest httpServletRequest){

        String userId = jsonObject.get("userId").toString();//第一次保存时userId会为空，
        String username = null;
        if(userId==""){
            String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
            // 获取 token 中的 user id
            try {
                userId = JWT.decode(token).getAudience().get(0);
                username = JWT.decode(token).getSubject();
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }else {
            username = jsonObject.get(username).toString();
        }

        //String version = jsonObject.get("version").toString();
        Object data = jsonObject.get("data");
        String name = jsonObject.get("name").toString();
        String desc = jsonObject.get("desc").toString();
        String image = jsonObject.get("image").toString();
        boolean shared = (boolean) jsonObject.get("shared");

        String topologie_id = jsonObject.get("id").toString();



        int star = 0;
        int hot = 0;

        Topologie topologie = null;
        if(topologie_id==""){
            topologie = new Topologie();
            topologie_id = topologie.getUUID();
        }else {
            star = (int)jsonObject.get("star");
            hot = (int) jsonObject.get("hot");
        }

        topologie.setId(topologie_id);
        topologie.setData(data);
        topologie.setName(name);
        topologie.setData(desc);
        topologie.setImage(image);
        topologie.setUserId(Integer.parseInt(userId));
        topologie.setUsername(username);
        topologie.setShared(shared);
        topologie.setStar(star);
        topologie.setHot(hot);
        topologie.setCreatedAt(new Date().getTime());
        System.out.println(topologie.toString());
        topologieService.save(topologie);


        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id",topologie_id);
        return sMap;




    }
}
