package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//JPA설정을 모으는 클래스 , 각종 설정을 잡을대
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        //인증기능을 사용하면, 누가 인증했는지 알게 되기때문에
        //auditing에서 이름을 넣는것, 모두 uno로 들어가게 된다.
        return() -> Optional.of("uno"); //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될때, 수정하자

    }
}
