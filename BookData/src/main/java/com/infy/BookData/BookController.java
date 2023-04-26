package com.infy.BookData;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@Autowired
	BookService bookService;

	// Api for saving a book
	@PostMapping("/books")
	public ResponseEntity<?> addBookApi(@RequestBody BookEntity addBook) {
		try {
			if (addBook.getBookName().isBlank() || addBook.getBookAuth().isBlank()) {
				throw new BookDataException("Book Name or Author can not be Null");
			}
			return new ResponseEntity<>(bookService.addBook(addBook), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	// Api for getting all books
	@GetMapping("/books")
	public ResponseEntity<?> getAllBookApi() {
		try {
			if (bookService.getAllBook().isEmpty() || bookService.getAllBook().contains(null)) {
				throw new BookDataException("No book data available");
			}
			return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
		} catch (BookDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// get book by id
	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBookByIdApi(@PathVariable("id") String bookId) {
		try {
			if (bookService.getAllBook().isEmpty() || bookService.getAllBook().contains(null)) {
				throw new BookDataException("No book data available");
			}
			if (bookId.isBlank() || Integer.parseInt(bookId) <= 0) {
				throw new BookDataException("Book id can not be null or 0 or Negetive");
			}
			return new ResponseEntity<>(bookService.getBookById(Integer.parseInt(bookId)), HttpStatus.FOUND);
		} catch (BookDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong!!", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	// Update book data by id..
	@PutMapping("books/{id}")
	public ResponseEntity<Object> updateBookApi(@PathVariable("id") String id, @RequestBody BookEntity book) {
		try {
			if (bookService.getAllBook().isEmpty() || bookService.getAllBook().contains(null)) {
				throw new BookDataException("No book data available");
			}
			if (id.isBlank() || Integer.parseInt(id) <= 0) {
				throw new BookDataException("Book id can not be null or 0 or Negetive");
			}
			return new ResponseEntity<>(bookService.updateBook(Integer.parseInt(id), book), HttpStatus.FOUND);
		} catch (BookDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Delete all books
	@DeleteMapping("/books")
	public ResponseEntity<Object> deleteAllBookApi() {
		try {
			if (bookService.getAllBook().isEmpty() || bookService.getAllBook().contains(null)) {
				throw new BookDataException("No book data available");
			}
			return new ResponseEntity<>(bookService.deleteAllBooks(), HttpStatus.OK);
		} catch (BookDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

	// delete a book
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Object> deleteBookApi(@PathVariable("id") String bookId) {
		try {
			if (bookService.getAllBook().isEmpty() || bookService.getAllBook().contains(null)) {
				throw new BookDataException("No book data available");
			}
			if (bookId.isBlank() || Integer.parseInt(bookId) <= 0) {
				throw new BookDataException("Book id can not be null or 0 or Negetive");
			}
			return new ResponseEntity<>(bookService.deleteBook(Integer.parseInt(bookId)), HttpStatus.OK);
		} catch (BookDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
