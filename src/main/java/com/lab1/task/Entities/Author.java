package com.lab1.task.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.MERGE)
    private List<Series> series;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.MERGE)
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String date_of_birth) {
        this.name = name;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }

    public void addSeries(Series theSeries){

        if (series == null){
            series = new ArrayList<>();
        }
        series.add(theSeries);
    }

    public void addBook(Book theBook){

        if (books == null){
            books = new ArrayList<>();
        }
        books.add(theBook);
    }
}