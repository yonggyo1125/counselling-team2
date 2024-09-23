package com.thxforservice.global.tests;


import com.thxforservice.member.constants.Authority;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory= MockSecurityContextFactory.class)
public @interface MockMember {


   long memberSeq() default 1L;
    String gid() default "testgid";
    String email() default "user01@test.org";
//    String uniqueGid = "testgid" + System.currentTimeMillis();
//    String uniqueEmail = "user" + System.currentTimeMillis() + "@test.org";

    String password() default "_aA123456";
    String userName() default "TestUser";
    String mobile() default "01010001000";
   Authority authority() default Authority.STUDENT;
}
