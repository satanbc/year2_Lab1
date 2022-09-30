package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @OneToMany(mappedBy = "authors",
                cascade = CascadeType.ALL)
    private List<Series> series;

    @OneToMany(mappedBy = "authors",
            cascade = CascadeType.ALL)
    private List<Book> books;

    public Authors() {
    }

    public Authors(String name, String date_of_birth) {
        this.name = name;
        this.date_of_birth = date_of_birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "name='" + name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }
}