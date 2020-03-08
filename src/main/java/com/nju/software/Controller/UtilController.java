package com.nju.software.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UtilController {

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/image")
    public Map<String,Object> profile(){
        Map<String,Object> sMap = new HashMap<>();
        String url = "/image/topology/thumb_"+new Date().getTime()+".png";
        sMap.put("url",url);
        return sMap;
    }


}

