package kr.res.store.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryCdEntity is a Querydsl query type for CategoryCdEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryCdEntity extends EntityPathBase<CategoryCdEntity> {

    private static final long serialVersionUID = 674776554L;

    public static final QCategoryCdEntity categoryCdEntity = new QCategoryCdEntity("categoryCdEntity");

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCategoryCdEntity(String variable) {
        super(CategoryCdEntity.class, forVariable(variable));
    }

    public QCategoryCdEntity(Path<? extends CategoryCdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryCdEntity(PathMetadata metadata) {
        super(CategoryCdEntity.class, metadata);
    }

}

