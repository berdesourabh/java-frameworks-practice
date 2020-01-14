package com.books.app.controller;

import com.books.app.dto.AssignBooksRequest;
import com.books.app.model.Reader;
import com.books.app.service.ReaderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reader")
@Api(value = "Reader APIs", description = "apis for Reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Reader> readers = readerService.getAll();
        return new ResponseEntity<List<Reader>>(readers, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Reader reader) {
        Reader newReader = readerService.create(reader);
        return new ResponseEntity<Reader>(newReader, HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignBooks(@RequestParam("readerId") Long readerId, @RequestBody AssignBooksRequest booksRequest) {

        Reader reader = readerService.assignBooks(booksRequest.getBookIds(), readerId);

        return new ResponseEntity<Reader>(reader, HttpStatus.OK);
    }
}
