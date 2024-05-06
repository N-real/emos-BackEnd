package com.nreal.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nreal.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;


/**
 * @Entity dao.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    boolean haveRootUser();

    Integer searchIdByOpenId(String openId);

    Set<String> searchUserPermissions(int userId);

    void insert(HashMap param);
}




