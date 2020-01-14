package com.books.app.dto;

import java.util.List;

public class BooksRestCollection {

    List<BookRestDto> books;

    public List<BookRestDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookRestDto> books) {
        this.books = books;
    }
}
