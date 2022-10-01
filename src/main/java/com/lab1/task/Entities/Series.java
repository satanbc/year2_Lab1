package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series", cascade = CascadeType.ALL)
    @OrderBy("release_year")
    private List<Book> books;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series", cascade = CascadeType.ALL)
    private List<Character> characters;

    public Series() {
    }

    public Series(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void addBook(Book theBook){

        if (books == null){
            books = new ArrayList<>();
        }
        books.add(theBook);
    }

    public void addCharacter(Character theCharacter){

        if (characters == null){
            characters = new ArrayList<>();
        }
        characters.add(theCharacter);
    }

    @Override
    public String toString() {
        return "Series{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
