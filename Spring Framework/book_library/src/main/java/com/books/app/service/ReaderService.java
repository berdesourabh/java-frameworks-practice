package com.books.app.service;

import com.books.app.model.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> getAll();

    Reader create(final Reader reader);

    Reader assignBooks(List<Long> bookIds, final Long readerId);

}
