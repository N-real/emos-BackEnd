package com.nreal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nreal.dao.entity.Checkin;


/**
 *
 */
public interface CheckinService extends IService<Checkin> {

    public String validCanCheckIn(int userId,String date);

}
