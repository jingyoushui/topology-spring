package com.nju.software.service;

import com.nju.software.Bean.ReportList;
import com.nju.software.Dao.ReportListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportListService {

    @Autowired
    private ReportListDao reportListDao;

    public ReportList findReportListByTopologyId(String  id){
        return reportListDao.findReportListByTopologyId(id);
    }
    public void save(ReportList reportList){
        reportListDao.save(reportList);
    }
}
