package com.nreal.controller;

import com.nreal.common.response.Result;
import com.nreal.dto.LoginDto;
import com.nreal.dto.RegisterDto;
import com.nreal.service.UserService;
import com.nreal.web.shiro.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire;

    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterDto registerDto) {
        int userId = userService.registerUser(registerDto.getRegisterCode(),registerDto.getCode(),registerDto.getNickname(),registerDto.getPhoto());
        String token = jwtUtil.createToken(userId);
        Set<String> set = userService.searchUserPermissions(userId);
        saveCacheToken(token,userId);
        return Result.ok("用户注册成功").put("token",token).put("permission",set);
    }

    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginDto loginDto){
        int userId = userService.login(loginDto.getCode());
        String token = jwtUtil.createToken(userId);
        saveCacheToken(token,userId);
        Set<String> set = userService.searchUserPermissions(userId);
        return Result.ok("登录成功").put("token",token).put("permission",set);
    }

    private void saveCacheToken(String token,int userId){
        redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
    }
}
