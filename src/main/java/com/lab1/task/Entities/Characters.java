package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
public class Characters {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "series_name")
    private Characters characters;

    @ManyToMany(mappedBy = "books",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_has_characters",
            joinColumns = {@JoinColumn(name = "characters_name"), @JoinColumn(name = "characters_role")},
            inverseJoinColumns = @JoinColumn(name = "books_name")
    )
    private List<Book> books;

    public Characters() {
    }

    public Characters(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
