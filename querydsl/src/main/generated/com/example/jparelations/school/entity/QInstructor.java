package com.example.jparelations.school.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInstructor is a Querydsl query type for Instructor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInstructor extends EntityPathBase<Instructor> {

    private static final long serialVersionUID = -1446376017L;

    public static final QInstructor instructor = new QInstructor("instructor");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final ListPath<Lecture, QLecture> lectures = this.<Lecture, QLecture>createList("lectures", Lecture.class, QLecture.class, PathInits.DIRECT2);

    public QInstructor(String variable) {
        super(Instructor.class, forVariable(variable));
    }

    public QInstructor(Path<? extends Instructor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstructor(PathMetadata metadata) {
        super(Instructor.class, metadata);
    }

}

