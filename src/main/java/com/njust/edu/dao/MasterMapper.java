package com.njust.edu.dao;

import com.njust.edu.entity.Master;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMapper {
    int deleteByPrimaryKey(Integer masterId);

    int insert(Master record);

    int insertSelective(Master record);

    Master selectByPrimaryKey(Integer masterId);

    Master selectByName(String masterName);  //


    int updateByPrimaryKeySelective(Master record);

    int updateByPrimaryKey(Master record);
}