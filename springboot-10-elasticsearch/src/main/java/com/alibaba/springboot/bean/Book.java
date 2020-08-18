package com.alibaba.springboot.bean;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "alibaba",type = "book")
public class Book {
    private int id;
    private String bookName;
    private String author;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(int id, String bookName, String author) {

        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {

    }
}
