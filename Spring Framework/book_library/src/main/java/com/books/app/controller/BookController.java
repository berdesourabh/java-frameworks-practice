package com.books.app.controller;

import com.books.app.datamapper.EntityToRestMapper;
import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.dto.BookRestDto;
import com.books.app.event.CustomEventPublisher;
import com.books.app.exception.ApiException;
import com.books.app.model.Book;
import com.books.app.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
@Api(value = "Books APIs")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RestToEntityMapper restToEntityMapper;

    @Autowired
    private EntityToRestMapper entityToRestMapper;

    @Autowired
    private CustomEventPublisher publisher;

    @GetMapping("/")
    public ResponseEntity<List<BookRestDto>> getAll() {
        List<Book> books = bookService.getAll();
        List<BookRestDto> bookRestDto = books.stream().map(b -> entityToRestMapper.convertToBookRestDto(b)).collect(Collectors.toList());
        publisher.publish();
        return new ResponseEntity<>(bookRestDto, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<BookRestDto> create(@Valid @RequestBody BookRestDto request) throws ApiException {
        Book book = restToEntityMapper.convertToBook(request);
        Book newBook = bookService.create(book);
        BookRestDto bookRestDto = entityToRestMapper.convertToBookRestDto(newBook);
        return new ResponseEntity<>(bookRestDto, HttpStatus.CREATED);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookRestDto> update(@RequestBody BookRestDto request, @PathVariable Long bookId) throws ApiException {
        Book book = restToEntityMapper.convertToBook(request);
        Book updatedBook = bookService.update(bookId, book);
        BookRestDto bookRestDto = entityToRestMapper.convertToBookRestDto(updatedBook);
        return new ResponseEntity<>(bookRestDto, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable Long bookId) throws ApiException {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookRestDto> get(@PathVariable Long bookId) throws ApiException {
        Book book = bookService.get(bookId);
        BookRestDto bookRestDto = entityToRestMapper.convertToBookRestDto(book);
        return new ResponseEntity<BookRestDto>(bookRestDto, HttpStatus.OK);
    }

}