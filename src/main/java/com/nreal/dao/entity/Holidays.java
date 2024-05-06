package com.nreal.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 节假日表
 * @TableName tb_holidays
 */
@TableName(value ="tb_holidays")
@Data
public class Holidays implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 日期
     */
    private Date date;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}