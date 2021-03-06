package org.melusky.model.obj.centralPaJug08042015.repository;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.melusky.model.obj.centralPaJug08042015.Book;

/** 
 * Spring Data Repository for table: book.
 * @author autogenerated/custom
 */ 
public interface BookRepository extends JpaRepository<Book, Integer>, QueryDslPredicateExecutor<Book> {

	// Add any extra methods here. This file will not get overwritten unlike any other generated file
}
