package com.lavingroji.service;

import com.lavingroji.dto.JoinFormDto;
import com.lavingroji.entity.Member;
import com.lavingroji.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member, JoinFormDto joinFormDto){
        validateDuplicateMember(member);
        confirmPassword(joinFormDto);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public void confirmPassword(JoinFormDto joinFormDto){
        System.out.println("비밀번호 : " + joinFormDto.getPassword() + " 비밀번호 확인 : " + joinFormDto.getConfirmPassword());
        if(!joinFormDto.getPassword().equals(joinFormDto.getConfirmPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
