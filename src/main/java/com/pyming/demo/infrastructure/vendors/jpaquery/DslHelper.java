package com.pyming.demo.infrastructure.vendors.jpaquery;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


@Component
@AllArgsConstructor
public class DslHelper {
    private EntityManager entityManager;

    public <T> Page<T> page(JPAQuery<T> jpaQuery, Pageable pageable) {
        PathBuilder<?> builder = new PathBuilderFactory().create(jpaQuery.getType());
        Querydsl querydsl = new Querydsl(entityManager, builder);
        if (pageable == null) {
            pageable = QPageRequest.of(0, Integer.MAX_VALUE);
        }
        long total = jpaQuery.clone(entityManager).fetchCount();
        JPQLQuery<T> pagedQuery = querydsl.applyPagination(pageable, jpaQuery);
        List<T> result = total > pageable.getOffset() ? pagedQuery.fetch() : Collections.<T>emptyList();
        return new PageImpl<T>(result, pageable, total);
    }

    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
