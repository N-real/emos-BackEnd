package com.nreal.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nreal.common.exception.EmosException;
import com.nreal.dao.entity.User;
import com.nreal.dao.mapper.UserMapper;
import com.nreal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private UserMapper userMapper;

    private String getOpenId(String code){
        String url="https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url,map);
        JSONObject json = JSONUtil.parseObj(response);
        String openId = json.getStr("openid");
        if(openId==null || openId.length()==0){
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }

    @Override
    public int registerUser(String registerCode, String code, String nickname, String photo) {
        if(registerCode.equals("000000")) {
            boolean bool = userMapper.haveRootUser();
            if (!bool) {
                String openId = getOpenId(code);
                HashMap param = new HashMap();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", new Date());
                param.put("root", true);
                userMapper.insert(param);
                int id = userMapper.searchIdByOpenId(openId);
                return id;
            } else {
                throw new EmosException("无法绑定超级管理员账号");
            }
        }
        return 0;
    }

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions = userMapper.searchUserPermissions(userId);
        return permissions;
    }
}




