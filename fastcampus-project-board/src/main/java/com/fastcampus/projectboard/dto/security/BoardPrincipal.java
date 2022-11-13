package com.fastcampus.projectboard.dto.security;

import com.fastcampus.projectboard.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardPrincipal(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String memo
) implements UserDetails {
    //of로 바꿔서 팩토리 메소드로 전환
    public static BoardPrincipal of(String username, String password, String email, String nickname, String memo) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER); //나중의 확장을 고려해서

        return new BoardPrincipal(
                username,
                password,
                // Collection<? extends GrantedAuthority> authorities 형식이어야 하는데, set을 사용했기 때문에 타입을 바꿔준다.
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                email,
                nickname,
                memo
        );
    }
    //dto로 부터 oardPrincipal조립
    public static BoardPrincipal from(UserAccountDto dto) {
        return BoardPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }
    //이것으로 실제로 회원정보를 저장하는 것도 가능하다.
    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                password,
                email,
                nickname,
                memo
        );
    }


    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public enum RoleType {
        //권한은 단하나 =user
        USER("ROLE_USER");

        @Getter private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }

}