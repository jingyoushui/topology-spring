package com.nju.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.Topologie;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.TopologieService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("topology")
public class TopologieController {

    @Autowired
    public TopologieService topologieService;

    @Autowired
    private UUID uuid;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get/{id}")
    public Topologie findTopologieById(@PathVariable(name = "id") String id){
        System.out.println("id:"+id);
        return topologieService.findTopologieById(id);
    }


    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String,Object> save(@RequestBody Topologie topologie, HttpServletRequest httpServletRequest){

        String userId = topologie.getUserId();//第一次保存时userId会为空，
        String username;
        if(userId==""){
            String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
            // 获取 token 中的 user id
            try {
                userId = JWT.decode(token).getAudience().get(0);
                username = JWT.decode(token).getSubject();
                topologie.setUserId(userId);
                topologie.setUsername(username);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }

        String topologie_id = topologie.getId();

        if(topologie_id==""){
            topologie_id = uuid.getUUID();
            topologie.setId(topologie_id);
            topologie.setCreatedAt(new Date().getTime());
        }else {
            topologie.setUpdatedAt(new Date().getTime());
        }
        System.out.println(topologie.toString());
        topologieService.save(topologie);
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id",topologie_id);
        return sMap;

    }
    @CrossOrigin
    @ResponseBody
    @PatchMapping(path = "/update")
    public void updateTopologie(@RequestBody JSONObject jsonParam){

        String id = jsonParam.get("id").toString();
        Topologie t = topologieService.findTopologieById(id);
        if(jsonParam.containsKey("shared")){
            boolean shared = (boolean) jsonParam.get("shared");
            t.setShared(shared);
        }
        if(jsonParam.containsKey("name")){
            String name = (String) jsonParam.get("name");
            t.setName(name);
        }
        if(jsonParam.containsKey("desc")){
            String desc  = (String) jsonParam.get("desc");
            t.setDesc(desc);
        }

        topologieService.save(t);

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/getShared")
    public Map<String,Object> getShared(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageCount")int pageCount){


        Map<String,Object> sMap = topologieService.findTopologiesByShared(true,pageIndex,pageCount);
        return sMap;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/getByUserId")
    public Map<String,Object> getTopologyByUserId(HttpServletRequest httpServletRequest, @RequestParam("pageIndex")int pageIndex,@RequestParam("pageCount")int pageCount){

        String userId = "";
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Map<String,Object> sMap = topologieService.findTopologiesByUserId(userId,pageIndex,pageCount);
        return sMap;
    }

    @CrossOrigin
    @ResponseBody
    @DeleteMapping(path = "/deleteTopology/{id}")
    public int deleteTopologyByIdAndUserId(HttpServletRequest httpServletRequest,@PathVariable(name = "id") String id){

        String userId = "";
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        int ret = topologieService.deleteTopologieByIdAndUserId(id,userId);
        return ret;
    }
}
