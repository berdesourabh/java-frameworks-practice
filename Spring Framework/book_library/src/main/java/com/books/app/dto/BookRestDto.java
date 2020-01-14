package com.books.app.dto;

public class BookRestDto {

    private String name;
    private String author;

    public BookRestDto(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public BookRestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
