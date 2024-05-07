package com.nreal.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nreal.dao.entity.Holidays;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Entity dao.domain.Holidays
 */
@Mapper
public interface HolidaysMapper extends BaseMapper<Holidays> {
    public Integer searchTodayIsHolidays();


}




