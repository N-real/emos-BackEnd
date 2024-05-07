package com.nreal.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nreal.common.SystemConstants;
import com.nreal.dao.entity.Checkin;
import com.nreal.dao.mapper.CheckinMapper;
import com.nreal.dao.mapper.HolidaysMapper;
import com.nreal.dao.mapper.WorkdayMapper;
import com.nreal.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 *
 */
@Service
@Scope("prototype")
public class CheckinServiceImpl extends ServiceImpl<CheckinMapper, Checkin> implements CheckinService {

    @Autowired
    private SystemConstants constants;

    @Autowired
    private HolidaysMapper holidaysMapper;

    @Autowired
    private WorkdayMapper workdayMapper;

    @Autowired
    private CheckinMapper checkinMapper;

    @Override
    public String validCanCheckIn(int userId, String date) {
        boolean e1 = holidaysMapper.searchTodayIsHolidays()!=null?true:false;
        boolean e2 = workdayMapper.searchTodayIsWorkdays()!=null?true:false;
        String type = "工作日";
        if(DateUtil.date().isWeekend())
            type = "节假日";
        if(e1)
            type = "节假日";
        else if(e2)
            type = "工作日";
        if(type.equals("节假日")){
            return "节假日卷尼玛";
        }else{
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + constants.attendanceStartTime;
            String end = DateUtil.today() + " " + constants.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(start);
            DateTime attendanceEnd = DateUtil.parse(end);
            if(now.isBefore(attendanceStart)){
                return "未到考勤开始时间";
            }
            else if(now.isAfter(attendanceEnd)){
                return "超过考勤结束时间";
            }else {
                HashMap map=new HashMap();
                map.put("userId",userId);
                map.put("date",date);
                map.put("start",start);
                map.put("end",end);
                boolean bool=checkinMapper.haveCheckin(map)!=null?true:false;
                return bool?"今日已经考勤，不用重复考勤" : "考勤成功";
            }
        }
    }
}




