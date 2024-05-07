package com.nreal.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nreal.dao.entity.Checkin;

import java.util.HashMap;


/**
 * @Entity dao.domain.Checkin
 */
public interface CheckinMapper extends BaseMapper<Checkin> {

    public Integer haveCheckin(HashMap map);
}




