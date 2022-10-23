package com.fastcampus.projectboard.repository;


import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        //현재 이 기능에 의해서 article에 있는 모든 필드들에 대한 검색이 열려있는데 ,
        //선택적인 필드에 대한 검색이 가능하게
        //리스팅을 하지 않은 프로퍼티는 검색이 되지 않도록 - 기본값은 false
        bindings.excludeUnlistedProperties(true);
        //원하는 필드를 춛가하는 것
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        //검색파라미터는 하나만 받기때문에 first+람다식 이용
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        // likeIgnoreCase는 ' ' containsIgnoreCase는 와일드카드를 쓰기때문에  % % 더 편리함
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        //더 크거나 작거나 검색하는 것이아닌, 원하는 날짜만 검색하기 때문에 eq
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);

        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}