package org.melusky.model.pojo;

import com.mysema.query.annotations.QueryEntity;

import java.util.Date;

/**
 * Created by mmelusky on 8/4/2015.
 */
@QueryEntity
public class Book {

    private String bookName;
    private Author author;
    private String isbn;
    private Date dateCreated;

    public Book(String bookName, Author author, String isbn, Date dateCreated) {
        this.bookName = bookName;
        this.author = author;
        this.isbn = isbn;
        this.dateCreated = dateCreated;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
