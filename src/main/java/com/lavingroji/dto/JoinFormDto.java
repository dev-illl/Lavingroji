package com.lavingroji.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter @Setter
public class JoinFormDto {

    @NotBlank(message = "이름을 입력 해주세요")
    private String name;

    @NotBlank(message = "Email을 입력 해주세요")
    private String email;

    @NotBlank(message = "비밀번호는 반드시 입력 해주세요")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 다시한번 입력 해주세요")
    private String confirmPassword;

    @NotBlank(message = "핸드폰 번호를 입력 해주세요")
    @Length(max = 11, message = "핸드폰 번호만 입력 해주세요")
    private String phone;

    private String gender;

    @NotBlank(message = "배송 받으실 주소를 입력 해주세요")
    private String address;
}