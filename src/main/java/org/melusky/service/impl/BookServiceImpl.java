package org.melusky.service.impl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.PostgresTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.Predicate;
import org.melusky.model.obj.centralPaJug08042015.repository.BookRepository;
import org.melusky.model.pojo.Book;
import org.melusky.model.pojo.PojoBookRepository;
import org.melusky.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// Import static query DSL
import static com.mysema.query.collections.CollQueryFactory.*;

/**
 * Created by mmelusky on 8/4/2015.
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private PojoBookRepository pojoBookRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksPojo() {
        return pojoBookRepository.getBookList();
    }

    @Override
    public List<Book> getBooksPojoFiltered(String name) {

        /*
        List<Book> result = new ArrayList<>();
        for (Book book : pojoBookRepository.getBookList())
        {
            if (book.getBookName().contains(name)) {
                result.add(book);
            }
        }
        return result;
        */
        org.melusky.model.pojo.QBook qBook = org.melusky.model.pojo.QBook.book;
        return from(qBook, pojoBookRepository.getBookList())
                .where(qBook.bookName.toLowerCase().contains(name.toLowerCase())).list(qBook);

    }

    @Override
    public List<String> getBooksSqlFiltered(String name) {

        org.melusky.querydsl.sql.QBook qBook = org.melusky.querydsl.sql.QBook.book;
        SQLTemplates dialect = PostgresTemplates.builder().printSchema().build();
        SQLQuery query = null;
        try {
            query = new SQLQuery(dataSource.getConnection(), dialect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.from(qBook).where(qBook.bookName.toLowerCase().contains(name.toLowerCase())).list(qBook.bookName);
    }

    @Override
    public List<org.melusky.model.obj.centralPaJug08042015.Book> getBooksJpaFiltered(String name) {

        Iterable<org.melusky.model.obj.centralPaJug08042015.Book> it =  bookRepository.findAll(findByNamePredicate(name));
        return StreamSupport.stream(it.spliterator(), false).collect(Collectors.toList());

    }

    private Predicate findByNamePredicate(String name)
    {
        org.melusky.model.obj.centralPaJug08042015.QBook qBook = org.melusky.model.obj.centralPaJug08042015.QBook.book;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qBook.bookName.toLowerCase().contains(name.toLowerCase()));
        return booleanBuilder;
    }
}
