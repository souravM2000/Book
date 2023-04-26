package com.infy.BookData;

import java.util.List;

public interface BookService {

	public BookEntity addBook(BookEntity addBook);

	public List<BookEntity> getAllBook();

	public BookEntity getBookById(Integer id) throws BookDataException;

	public BookEntity updateBook(Integer bookId, BookEntity book) throws BookDataException;

	public BookEntity deleteBook(Integer id) throws BookDataException;

	String deleteAllBooks();

}
