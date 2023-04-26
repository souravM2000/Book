package com.infy.BookData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	public BookEntity findByBookId(Integer bookId);
}
