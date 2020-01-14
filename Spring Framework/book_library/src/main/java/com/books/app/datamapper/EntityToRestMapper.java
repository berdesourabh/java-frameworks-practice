package com.books.app.datamapper;

import com.books.app.dto.BookRestDto;
import com.books.app.model.Book;
import org.springframework.stereotype.Component;

@Component
public class EntityToRestMapper {

    private EntityToRestMapper() {
    }

    public BookRestDto convertToBookRestDto(final Book book) {
        BookRestDto bookRestDto = new BookRestDto();
        bookRestDto.setName(book.getName());
        bookRestDto.setAuthor(book.getAuthor());

        return bookRestDto;
    }
}
