package com.nreal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nreal.dao.entity.Config;
import com.nreal.dao.mapper.ConfigMapper;
import com.nreal.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config>
    implements ConfigService {

}




