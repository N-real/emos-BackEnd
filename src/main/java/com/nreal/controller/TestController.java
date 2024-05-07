package com.nreal.controller;

import com.nreal.common.response.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/addUser")
    @RequiresPermissions(value = {"ROOT","USER:INSERT"},logical = Logical.OR)
    public Result addUser(){
        return Result.ok("用户添加成功");
    }

}
