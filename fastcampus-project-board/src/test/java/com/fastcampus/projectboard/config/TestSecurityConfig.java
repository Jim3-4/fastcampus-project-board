package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

//securityFilterChain()부분을 쓰기위해
@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    //인증과 관련된 테스트만, 특정한 주기에 맞춰서 특정한 코드가 실행되게끔
    //계정정보를 가짜로 하나 더 만들어서 잘 보장된 테스트를 돌릴 수 있도록
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "unoTest",
                "pw",
                "uno-test@email.com",
                "uno-test",
                "test memo"
        )));
    }

}