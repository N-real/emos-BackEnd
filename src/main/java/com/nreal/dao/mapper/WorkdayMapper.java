package com.nreal.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nreal.dao.entity.Workday;


/**
 * @Entity dao.domain.Workday
 */
public interface WorkdayMapper extends BaseMapper<Workday> {

    public Integer searchTodayIsWorkdays();
}




