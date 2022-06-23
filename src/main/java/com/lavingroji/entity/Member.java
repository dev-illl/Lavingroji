package com.lavingroji.entity;

import com.lavingroji.constant.Role;
import com.lavingroji.dto.JoinFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "member")
@EqualsAndHashCode(callSuper=false)
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;

    private String gender;


    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(JoinFormDto joinFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(joinFormDto.getName());
        member.setEmail(joinFormDto.getEmail());
        String password = passwordEncoder.encode(joinFormDto.getPassword());
        String rePassword = passwordEncoder.encode(joinFormDto.getConfirmPassword());
        member.setPassword(password);
        member.setConfirmPassword(rePassword);
        member.setPhone(joinFormDto.getPhone());
        member.setGender(joinFormDto.getGender());
        member.setAddress(joinFormDto.getAddress());
        member.setRole(Role.USER);
        return member;
    }
}