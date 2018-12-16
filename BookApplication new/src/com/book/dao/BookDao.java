package com.book.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.book.bean.Book;
import com.book.bean.Customer;
import com.book.exception.BookException;

public interface BookDao {
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException;
	public Book viewBookDetails(String bookId) throws BookException, ClassNotFoundException, IOException, SQLException;
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException;
	public String addCustomer(Customer customer) throws ClassNotFoundException, IOException, SQLException;
	public List<Book> calPrice(String[] arr) throws ClassNotFoundException, IOException, SQLException;
	public String deleteBook(Book book) throws ClassNotFoundException, IOException, SQLException;
	public Book updateDetails(int bookPrice, String bookName) throws BookException, ClassNotFoundException, IOException, SQLException;
	
	
}

