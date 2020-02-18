package com.itcast.demo.dao;

import com.itcast.demo.domain.JdItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JdDao extends JpaRepository<JdItemInfo,BigInteger>,JpaSpecificationExecutor<JdItemInfo> {
}
