package com.baeldung.spring.dao.generics;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDAO<T> implements IGenericDao<T> {
    //
}