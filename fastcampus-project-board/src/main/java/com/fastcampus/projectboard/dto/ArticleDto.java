package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Article;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    //DTO로 부터 엔티티 생성 
    //ARticle은 dto 의 존재를 몰라도된다. dto만 연관관계 매핑을 하기위해 dto만 article의 존재를 알고 있다.
    public Article toEntity() {
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }

}