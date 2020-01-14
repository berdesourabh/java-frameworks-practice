package com.books.app.service.impl;

import com.books.app.exception.ApiError;
import com.books.app.exception.ApiException;
import com.books.app.exception.ReasonCode;
import com.books.app.model.Book;
import com.books.app.repository.BookRepository;
import com.books.app.repository.ReaderRepository;
import com.books.app.service.BookService;
import com.books.app.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(Book book) throws ApiException {

        if (isBookValid(book)) {
            return bookRepository.save(book);
        } else
            throw new ApiException(new ApiError("BookServiceImpl::create", ReasonCode.CREATION_FAILED_003.getCode(), MessagesUtil.getMessage("creation.failed"), HttpStatus.BAD_REQUEST));
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) throws ApiException {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        throw new ApiException(new ApiError("BookServiceImpl::update", "001", "updation failed", HttpStatus.BAD_REQUEST));

    }

    @Override
    @Transactional
    public void delete(Long id) throws ApiException {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            bookRepository.delete(existingBook);
        } else
            throw new ApiException(new ApiError("BookServiceImpl::delete", "001", "deletion failed", HttpStatus.BAD_REQUEST));


    }

    @Override
    @Transactional
    public Book get(Long id) throws ApiException {
        Book existingBook = bookRepository.getOne(id);
        if (isBookValid(existingBook)) {
            return existingBook;
        }
        throw new ApiException(new ApiError("BookServiceImpl::get", "001", "Fetch failed", HttpStatus.BAD_REQUEST));

    }

    private boolean isBookValid(Book book) {
        return book != null && !book.getName().isEmpty();
    }
}
