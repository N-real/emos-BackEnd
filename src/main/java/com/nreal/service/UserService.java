package com.nreal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nreal.dao.entity.User;


import java.util.Set;

/**
 *
 */
public interface UserService extends IService<User> {

    int registerUser(String registerCode, String code, String nickname, String photo);

    Set<String> searchUserPermissions(int userId);

    int login(String code);

    User searchById(int userId);
}
