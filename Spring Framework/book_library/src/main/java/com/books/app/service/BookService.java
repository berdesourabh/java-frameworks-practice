package com.books.app.service;

import com.books.app.exception.ApiException;
import com.books.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book create(Book book) throws ApiException;

    Book update(Long id, Book book) throws ApiException;

    void delete(Long id) throws ApiException;

    Book get(Long id) throws ApiException;
}
