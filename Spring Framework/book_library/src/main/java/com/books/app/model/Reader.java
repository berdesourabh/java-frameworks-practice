package com.books.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "reader")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "name can not be null")
    private String name;

    private short age;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private Set<Book> books;

    public Reader() {
    }

    public Reader(String name, short age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
