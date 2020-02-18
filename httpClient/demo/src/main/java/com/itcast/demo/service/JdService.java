package com.itcast.demo.service;

import com.itcast.demo.domain.JdItemInfo;

import java.util.List;

public interface JdService {
    void save(JdItemInfo jdItemInfo);

    List<JdItemInfo> findAll(JdItemInfo jdItemInfo);
}
