package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        //SecurityContext를 가져온다 .
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated) //인증이 됐는지 확인이 되었으니
                //getPrincipal 인증정보를 꺼내오겟다.
                .map(Authentication::getPrincipal)
                .map(BoardPrincipal.class::cast)//타입캐스팅 (람다식보다 클래스를 불러와서 그 안에  cast를 불러오는게 더 좋다)
                .map(BoardPrincipal::getUsername); //UserName 이것이 실제 사용자 정보
    }

}