package org.melusky.controller;

import org.melusky.model.pojo.Book;
import org.melusky.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mmelusky on 8/4/2015.
 */

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String helloWorld()
    {
        return "Hello World!";
    }

    @RequestMapping("/books/pojo")
    public List<Book> getPojoList()
    {
        return bookService.getBooksPojo();
    }

    @RequestMapping("/books/pojo/{name}")
    public List<Book> getPojoListFilter(@PathVariable("name") String name)
    {
        return bookService.getBooksPojoFiltered(name);
    }

    @RequestMapping("/books/sql/{name}")
    public List<String> getBookSql(@PathVariable("name") String name)
    {
        return bookService.getBooksSqlFiltered(name);
    }

    @RequestMapping("/books/jpa/{name}")
    public List<org.melusky.model.obj.centralPaJug08042015.Book> getJpaListFilter(@PathVariable("name") String name)
    {
        return bookService.getBooksJpaFiltered(name);
    }

}
