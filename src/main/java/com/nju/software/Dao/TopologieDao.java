package com.nju.software.Dao;

import com.nju.software.Bean.Topologie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopologieDao  extends MongoRepository<Topologie,Integer> {

    public Topologie findTopologieById(String  id);



}
