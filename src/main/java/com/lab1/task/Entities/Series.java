package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {

    @Id
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authors_name")
    private Series series;

    @OneToMany(mappedBy = "series",
            cascade = CascadeType.ALL)
    private List<Book> books;

    public Series() {
    }

    public Series(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
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
        return "Series{" +
                "name='" + name + '\'' +
                '}';
    }
}
