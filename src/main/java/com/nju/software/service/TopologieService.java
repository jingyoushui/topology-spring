package com.nju.software.service;


import com.nju.software.Bean.Topologie;
import com.nju.software.Dao.TopologieDao;
import com.nju.software.util.PageModel;
import com.nju.software.util.SpringbootPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TopologieService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TopologieDao topologieDao;

    public Topologie findTopologieById(String id){
        return topologieDao.findTopologieById(id);
    }

    public void save(Topologie topologie){
        topologieDao.save(topologie);
    }

    public Map<String,Object> findTopologiesByShared(boolean shared, Integer pageIndex,Integer pageCount){
        SpringbootPageable pageable = new SpringbootPageable();
        PageModel pm=new PageModel();
        Query query = new Query();
        List<Sort.Order> orders = new ArrayList<Sort.Order>();  //排序
        orders.add(new Sort.Order(Sort.Direction.DESC, "createdAt"));
        Sort sort = Sort.by(orders);
        Criteria criteria = new Criteria();
        //sid查询
        criteria.and("shared").is(shared);
        query.addCriteria(criteria);
        // 开始页
        pm.setPagenumber(pageIndex);
        // 每页条数
        pm.setPagesize(pageCount);
        // 排序
        pm.setSort(sort);
        pageable.setPage(pm);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Topologie.class);

        // 查询
        List<Topologie> list = mongoTemplate.find(query.with(pageable), Topologie.class);
        // 将集合与分页结果封装
        Map<String,Object> smap = new HashMap<>();
        smap.put("list",list);
        smap.put("count",count);
//        Page<Topologie> pagelist = new PageImpl<Topologie>(list, pageable, count);
        return smap;

    }
}
