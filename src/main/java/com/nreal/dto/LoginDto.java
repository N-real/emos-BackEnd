package com.nreal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank(message = "临时授权不能为空")
    private String code;
}
