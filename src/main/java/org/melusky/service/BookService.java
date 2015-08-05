package org.melusky.service;

import org.melusky.model.pojo.Book;

import java.util.List;

/**
 * Created by mmelusky on 8/4/2015.
 */
public interface BookService {

    // Get books via pojo style
    public List<Book> getBooksPojo();
    List<Book> getBooksPojoFiltered(String name);

    // Get books via service
    List<String> getBooksSqlFiltered(String name);

    // JPA
    List<org.melusky.model.obj.centralPaJug08042015.Book> getBooksJpaFiltered(String name);
}
