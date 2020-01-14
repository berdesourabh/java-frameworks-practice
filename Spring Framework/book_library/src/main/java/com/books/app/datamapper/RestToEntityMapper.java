package com.books.app.datamapper;

import com.books.app.dto.BookRestDto;
import com.books.app.model.Book;
import org.springframework.stereotype.Component;

@Component
public class RestToEntityMapper {

    private RestToEntityMapper() {
    }

    public Book convertToBook(final BookRestDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());

        return book;
    }
}
