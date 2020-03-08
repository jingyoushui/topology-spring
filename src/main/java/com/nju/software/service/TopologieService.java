package com.nju.software.service;


import com.nju.software.Bean.Topologie;
import com.nju.software.Dao.TopologieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class TopologieService {

    @Autowired
    TopologieDao topologieDao;

    public Topologie findTopologieById(String id){
        return topologieDao.findTopologieById(id);
    }

    public void save(Topologie topologie){
        topologieDao.save(topologie);
    }




}
