package com.myapp.toyee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -252894127L;

    public static final QMember member = new QMember("member1");

    public final StringPath createdDatetime = createString("createdDatetime");

    public final StringPath memberId = createString("memberId");

    public final NumberPath<Long> memberNum = createNumber("memberNum", Long.class);

    public final StringPath useYsno = createString("useYsno");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

