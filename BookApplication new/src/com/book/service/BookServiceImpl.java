package com.book.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.book.bean.Book;
import com.book.bean.Customer;
import com.book.dao.BookDao;
import com.book.dao.BookDaoImpl;
import com.book.exception.BookException;



public class BookServiceImpl implements BookService{
    BookDao bookdao=new BookDaoImpl();
	@Override
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException {
		String bookseq;
		bookseq=bookdao.addBook(book);
		return bookseq;
	}

	@Override
	public Book viewBookDetails(String bookId) throws BookException, ClassNotFoundException, IOException, SQLException {
		//System.out.println(bookId);
		Book book=new Book();
				book=bookdao.viewBookDetails(bookId);
		
		return book;
		
	}

	@Override
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException {
		bookdao=new BookDaoImpl();
		List<Book> donorList=null;
		donorList=bookdao.retriveAll();
		return donorList;
		
	}

	public void validateBook(Book book) throws BookException {
		// TODO Auto-generated method stub
        List<String> validationErrors=new ArrayList<String>();
		
		if(!(isValidName(book.getBookName())))
		{
			validationErrors.add("\n book Name should be in Alphabets and minimum 3 characters long\n");
			
		}
		if(!(isValidName(book.getAuthorName())))
		{
			validationErrors.add("\n author Name should be in Alphabets and minimum 3 characters long\n");
			
		}
		if(!(isValidAmount(book.getBookPrice())))
		{
			validationErrors.add("Amount should be positive number");
		}
		
		if(!(validationErrors.isEmpty()))
		{
			throw new BookException(validationErrors+"");
		}
	}

	private boolean isValidAmount(int bookPrice) {
		
		return bookPrice>0;
	}

	private boolean isValidName(String bookName) {
		Pattern p=Pattern.compile("^[A-Z][a-z]{3,}$");
		Matcher phoneMatcher1=p.matcher(bookName);
		return phoneMatcher1.matches();
	}
	@Override
 public String addCustomer(Customer customer) throws ClassNotFoundException, IOException, SQLException {
		String customerseq;
		customerseq=bookdao.addCustomer(customer);
		return customerseq;
		
	}
	
	
	
	
	

	public void validateCustomer(Customer customer) throws BookException {
	    List<String> validationErrors=new ArrayList<String>();
		
			if(!(isValidCName(customer.getCustomerName())))
			{
				validationErrors.add("\n Customer Name should be in Alphabets and minimum 3 characters long\n");
				
			}
			if(!(isValidPhno(customer.getPhno())))
			{
				validationErrors.add("\n author Name should be in Alphabets and minimum 3 characters long\n");
				
			}
			if(!(isValidEmail(customer.getEmailid())))
			{
				validationErrors.add("invalid emailid");
			}
			if(!(isValidAddress(customer.getAddress())))
			{
				validationErrors.add("Address should be greater than 5 characters");
				
			}

			
			if(!(validationErrors.isEmpty()))
			{
				throw new BookException(validationErrors+"");
			}
		
	}

	private boolean isValidAddress(String address) {
		
		return (address.length()>6);
	}

	private boolean isValidEmail(String emailid) {
		Pattern p=Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
		Matcher phoneMatcher=p.matcher(emailid);
		return phoneMatcher.matches();
		
	}

	private boolean isValidPhno(String phno) {
		Pattern p=Pattern.compile("^[6-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=p.matcher(phno);
		return phoneMatcher.matches();
		
	}

	private boolean isValidCName(String customerName) {
		Pattern p=Pattern.compile("^[A-Z][a-z]{3,}$");
		Matcher phoneMatcher1=p.matcher(customerName);
		return phoneMatcher1.matches();
		
	}

	@Override
	public String deleteBook(Book book) throws ClassNotFoundException, BookException, IOException, SQLException {
		String bookseq;
		bookseq=bookdao.deleteBook(book);
		return bookseq;
		
	}

	public Book updateDetails(int bookPrice, String bookName) throws ClassNotFoundException, BookException, IOException, SQLException {
		Book book = null;
		book = bookdao.updateDetails(bookPrice, bookName);
		
		return book;
		
	}

	/*public int calPrice(String[] arr) throws ClassNotFoundException, IOException, SQLException {
		bookdao=new BookDaoImpl();
		List<Book> cartList=null;
		cartList=bookdao.calPrice(arr);
		return cartList;*/
	}

	
	

	
	

	


