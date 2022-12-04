package com.akash.booksaloha.manager;

import java.util.ArrayList;
import java.util.Collection;

import com.akash.booksaloha.entity.Book;

public class BooksManager {
	private static BooksManager instance = new BooksManager();

	private BooksManager() {
	}

	public static BooksManager getInstance() {
		return instance;
	}

	public Collection<Book> getMyBooks() {
		Collection<Book> myBooks = new ArrayList<>();

		// First book
		Book book = new Book();
		book.setImageUrl("http://photo.goodreads.com/books/1170846378m/73968.jpg");
		book.setAuthor("Erich Segal");
		book.setRating(3.44);

		myBooks.add(book);

		// Second book
		book = new Book();
		book.setImageUrl("http://ecx.images-amazon.com/images/I/21WBe6pNO5L._SX106_.jpg");
		book.setAuthor("Lillian Eichler Watson");
		book.setRating(5.0);

		myBooks.add(book);

		return myBooks;
	}
}
