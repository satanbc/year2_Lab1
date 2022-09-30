package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table (name = "books")
public class Book {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "page_count")
    private String page_count;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authors_name")
    @JoinColumn(name = "series_name")
    private Book book;

    @ManyToMany(mappedBy = "books",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_has_characters",
            joinColumns = @JoinColumn(name = "books_name"),
            inverseJoinColumns = {@JoinColumn(name = "characters_name"), @JoinColumn(name = "characters_role")}
    )
    private List<Characters> characters;

    public Book() {
    }

    public Book(String name, String release_date, String page_count, String description) {
        this.name = name;
        this.release_date = release_date;
        this.page_count = page_count;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", page_count='" + page_count + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
