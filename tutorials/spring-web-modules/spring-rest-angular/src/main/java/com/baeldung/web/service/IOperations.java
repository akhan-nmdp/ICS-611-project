package com.baeldung.web.service;

import org.springframework.data.domain.Page;

public interface IOperations<T> {

    Page<T> findPaginated(final int page, final int size);

}
