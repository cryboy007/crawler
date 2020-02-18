package com.itcast.demo.service.impl;

import com.itcast.demo.dao.JdDao;
import com.itcast.demo.domain.JdItemInfo;
import com.itcast.demo.service.JdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdServiceImpl implements JdService {

    @Autowired
    private JdDao jdDao;
    @Override
    public void save(JdItemInfo jdItemInfo) {
        jdDao.save(jdItemInfo);
    }

    @Override
    public List<JdItemInfo> findAll(JdItemInfo jdItemInfo) {
        //声明查询条件
        Example<JdItemInfo> example = Example.of(jdItemInfo);
        //根据条件查询
        return jdDao.findAll(example);
    }
}
