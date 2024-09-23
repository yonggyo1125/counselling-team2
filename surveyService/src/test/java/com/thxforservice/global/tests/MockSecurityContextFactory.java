package com.thxforservice.global.tests;

import com.thxforservice.member.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

@Component
public class MockSecurityContextFactory implements WithSecurityContextFactory<MockMember> {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public SecurityContext createSecurityContext(MockMember mockMember) {

        Member member = new Member();
        member.setMemberSeq(1L);
        member.setEmail(mockMember.email());
        member.setPassword(encoder.encode(mockMember.password()));
        member.setUsername(mockMember.userName());
        member.setMobile(mockMember.mobile());
        member.setGid(mockMember.gid());





//        UserType userType = new Authorities();
//        authorities.setAuthority(mockMember.authority());
//        List<Authorities> items = List.of(authorities);
//        member.setAuthorities(items);
//
//        List<SimpleGrantedAuthority> _authorities = List.of(new SimpleGrantedAuthority(mockMember.authority().name()));

//        MemberInfo memberInfo = MemberInfo.builder()
//                .email(member.getEmail())
//                .password(member.getPassword())
//                .authorities(_authorities)
//                .member(member)
//                .build();
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberInfo, null, _authorities);

//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(token);

        //return context;
        return null;
    }
}
