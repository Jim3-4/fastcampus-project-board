package com.fastcampus.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
//Jackson이라는 json으로 바꿔주는 직렬화 도구를 사용하기에 Serializable 가 필요없어서 지운다.
public record ArticleUpdateDto( String title, String content,
                               String hashtag) {

    public static ArticleUpdateDto of( String title, String content, String hashtag) {
      return new ArticleUpdateDto( title, content, hashtag);
    }
}