package com.infy.BookData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	// List<BookEntity> bookList=new ArrayList<>();
	@Autowired
	BookRepository bookRepository;

	// to add book
	@Override
	public BookEntity addBook(BookEntity addBook) {
		return bookRepository.save(addBook);

	}

	// to get all book
	@Override
	public List<BookEntity> getAllBook() {
		return bookRepository.findAll();
	}

	// to get specific book by id
	@Override
	public BookEntity getBookById(Integer id) throws BookDataException {
//		BookEntity b = null;
//		boolean flag = false;
//		for (BookEntity book : bookList) {
//			if (book.getBookId().equals(id)) {
//				flag = true;
//				b = book;
//			}
//		}
//		if (flag == false) {
//			throw new BookDataException("Book id does not exits");
//		}	

		if (bookRepository.existsById(id)) {
			return bookRepository.findByBookId(id);
		} else {
			throw new BookDataException("BookId does not exists!");
		}
	}

	// update book
	@Override
	public BookEntity updateBook(Integer id, BookEntity newBook) throws BookDataException {
//		BookEntity b = null;
//		boolean flag = false;
//		for (BookEntity book : bookList) {
//			if (book.getBookId() == id) {
//				book.setBookName(newBook.getBookName());
//				book.setBookAuth(newBook.getBookAuth());
//				b = book;
//				flag = true;
//			}
//		}
//		if (flag == false) {
//			throw new BookDataException("Book id does not exits");
//		}
		
		if (bookRepository.existsById(id)) {
			newBook.setBookId(id);
			return bookRepository.save(newBook);
		} else {
			throw new BookDataException("BookId does not exists!");
		}

	}

	// delete all books
	@Override
	public String deleteAllBooks() {
		bookRepository.deleteAll();
		return "All books deleted";
	}

	// delete book by id
	@Override
	public BookEntity deleteBook(Integer id) throws BookDataException {
//		List<BookEntity> b = new ArrayList<>();
//		boolean flag = false;
//		for (BookEntity book : bookList) {
//			if (book.getBookId() == id) {
//				b.add(book);
//				flag = true;
//			}
//		}
//		if (flag == false) {
//			throw new BookDataException("Book id does not exits");
//		}
//
//		bookList.removeAll(b);
		if (bookRepository.existsById(id)) {
			BookEntity book = bookRepository.findByBookId(id);
			bookRepository.deleteById(id);
			return book;
		} else {
			throw new BookDataException("BookId does not exists!");
		}

	}

}
