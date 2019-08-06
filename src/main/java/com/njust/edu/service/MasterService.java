package com.njust.edu.service;

import com.njust.edu.controller.MasterController;
import com.njust.edu.dao.MasterMapper;
import com.njust.edu.entity.Master;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MasterService {

    @Resource
    private MasterMapper masterMapper;

    public int addMaster(Master master){
        return masterMapper.insert(master);
    }

    public boolean login(String masterName,String masterPwd){
        Master master=masterMapper.selectByName(masterName);
        boolean rs=master.getMasterPwd().equals(masterPwd);
        return rs;
    }

    public Master findMaster(int masterId){
        return masterMapper.selectByPrimaryKey(masterId);
    }

    public Master findByName(String masterName){
        return  masterMapper.selectByName(masterName);

    }





}
