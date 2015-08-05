package org.melusky.model.pojo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mmelusky on 8/4/2015.
 */

@Component
public class PojoBookRepository {

    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    @PostConstruct
    public void initData()
    {
        bookList = new ArrayList<>();
        Author a = new Author("Mike Melusky", new Date());

        bookList.add(new Book("War and Peace", a, "9879879324", new Date()));
        bookList.add(new Book("Pride and Prejudice", a, "234234324", new Date()));
        bookList.add(new Book("Wuthering Heights", a, "454564545", new Date()));
        bookList.add(new Book("1984", a, "234234243", new Date()));
    }
}
