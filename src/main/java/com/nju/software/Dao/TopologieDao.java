package com.nju.software.Dao;

import com.nju.software.Bean.Topologie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TopologieDao  extends MongoRepository<Topologie,Integer> {

    public Topologie findTopologieById(String  id);

//    public Page<Topologie> findTopologiesByShared(boolean shared, Pageable pageable);
}
