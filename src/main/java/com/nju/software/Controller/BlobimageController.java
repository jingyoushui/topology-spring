package com.nju.software.Controller;


import com.alibaba.fastjson.JSONObject;
import com.nju.software.Bean.Blobimage;
import com.nju.software.service.BlobimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BlobimageController {

    @Autowired
     private BlobimageService blobimageService;

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/upload_t_image")
    public Map<String,Object> save(@RequestParam("file") MultipartFile blobFile, @RequestParam("randomName")int randomname,
                                                     @RequestParam("public")boolean isPublic) throws IOException {


        Blobimage blobimage = new Blobimage();
        String name = blobimage.getUUID();
        String url = "thumb_"+name+".png";
        blobimage.setPath(url);
        blobimage.setIspublic(isPublic);
        blobimage.setRandomName(randomname);
        if(blobFile==null){
            blobimage.setFile(null);
        }else {
            blobimage.setFile(blobFile.getBytes());
        }
        String uri = blobimageService.save(blobimage);
        Map<String,Object> sMap = new HashMap<>();

        sMap.put("url",uri);
        return sMap;
    }
    @CrossOrigin
    @ResponseBody
    @DeleteMapping(path = "/delete_t_image/{id}")
    public void delete(@PathVariable(name = "id") String id){
        blobimageService.delete(id);
    }

    @CrossOrigin
    @GetMapping(path = "/blob/{id}")
    public ResponseEntity<byte[]> getblob(@PathVariable("id")String path){
        byte[] image = blobimageService.findBlob(path).getFile();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PatchMapping(path = "/blob/{id}")
    public Map<String,Integer> alterShared(@PathVariable("id")String path, @RequestBody JSONObject jsonParam){
        System.out.println(path);
        boolean ispublic = (boolean) jsonParam.get("public");
        int i = blobimageService.updateBlob(path,ispublic);

        Map<String,Integer> sMap = new HashMap<>();
        sMap.put("i",i);
        return sMap;
    }


}

