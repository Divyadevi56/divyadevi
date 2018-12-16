package com.book.pl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.book.bean.Book;
import com.book.bean.Customer;
import com.book.exception.BookException;
import com.book.service.BookService;
import com.book.service.BookServiceImpl;



public class BookMain {
	
	
	static Scanner sc=new Scanner(System.in);
	
	private static  BookServiceImpl bookServiceImpl;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, BookException {
		 Book book=new Book();                                             
		 BookService bookService=null;
		 bookServiceImpl=null;
		 Book book1=null;
		 String bookId=null;
		 String customerId=null;
		 Customer customer1=null;
			
			System.out.println();
			System.out.println();
			System.out.println(" Online Book Store");
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("enter choice");
			
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1: 
			      
				int option=0;
				while(true)
				{
				
			System.out.println("1.Add Book");
			System.out.println("2.Delete BookDetails");
			System.out.println("3.Update price");
			System.out.println("4.Exit");
			System.out.println("________________________________________\n");
			System.out.println("Select an option:");
			try
			{
				 option = sc.nextInt();
				Object bookName;
				switch(option) {
				case 1: 
					while(book1==null)
					{
						book1=populateBookDetails();
					}
					try {
						bookService=new BookServiceImpl();
						 bookId = bookService.addBook(book1);
							
							System.out.println("book details has been successfully registered");
							System.out.println("book Id is:"+bookId);
							
						}
						catch(BookException bookException)
						{
							bookException.printStackTrace();
							System.out.println("ERROR:"+bookException.getMessage());
						}
						finally
						{
							bookId=null;
							bookService=null;
							book1=null;
						}
						
						break;
				case 2: System.out.println("enter book name");
		                 // String bookName = sc.next();
					
					
					try {
					bookService=new BookServiceImpl();
					 bookId = bookService.deleteBook(book);
						
						System.out.println("book details has been successfully deleted");
						//System.out.println("book Id is:"+bookId);
						
					}
					catch(BookException bookException)
					{
						bookException.printStackTrace();
						System.out.println("ERROR:"+bookException.getMessage());
					}
					finally
					{
						bookId=null;
						bookService=null;
						book1=null;
					}
					
					break;
				case 4: System.exit(0);
				break;
				case 3:System.out.println(" Enter BookName");
				String bookName1 = sc.next();
				System.out.println("Enter bookPrice");
				int bookPrice = sc.nextInt();
				bookService = new BookServiceImpl();
				book = bookService.updateDetails(bookPrice,bookName1);
				System.out.println("updated successfully");
				break;
				default: System.out.println("Try Again!!!!!!!");
				
				break;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e);
			}
				}
	case 2:
			        
				
				while(true)
				{
				
				     System.out.println("1.Book details");
			         System.out.println("2.select book");
			         System.out.println("3.exit");
			         int c=0;
			         System.out.println("enter choice");
			         c=sc.nextInt();
			         switch(c)
			         {
			         case 1:
			        	   bookService = new BookServiceImpl();
							try {
								List<Book> bookList = new ArrayList<Book>();
								bookList = bookService.retriveAll();
 
								if (bookList != null) {
									Iterator<Book> i = bookList.iterator();
									while (i.hasNext()) {
										System.out.println(i.next());
									}
								} else {
									System.out.println("No books available");
								}

							}

							catch (BookException e) {

								e.printStackTrace();
							}

							break;
							
			         case 2:
			        	 
			        	 System.out.println("enter book name");
			                 //String bookName = sc.next();
						bookService=new BookServiceImpl();
						String bookName=null;
						book=bookService.viewBookDetails(bookName);
						if(book!=null)
						{
						System.out.println(book);
						}
						else 
						{
							System.out.println("book not available");
						}
						//String[] arr=null;
						//int x= calculatePrice(arr);
						System.out.println("if you want to order press 1 otherwise press 0 to exit");
						int i=sc.nextInt();
						try
						{
						 switch(i)
						 {
						 case 1: while(customer1==null)
							     {
								customer1=populateCustomerDetails();
							     }
						 try {
								bookService=new BookServiceImpl();
								customerId = bookService.addCustomer(customer1);
									
									System.out.println("customer details has been successfully registered");
									System.out.println("if you want to buy press 1 otherwise press 0 to exit");
									int j=sc.nextInt();  
									switch(j)
									  {
									case 1:System.out.println("Your order is placed, Visit Again");
									case 0:System.exit(0);
									  }
									//System.out.println("customer Id is:"+customerId);
									
								}
								finally
								{
									bookId=null;
									bookService=null;
									book1=null;
								}
								
								break;
						 case 0: System.exit(0);
						         break;
						default: System.out.println("Try Again!!!!!!!");
						//System.out.println("");
						
						break;
						}
					}catch(Exception e1)
					{
						e1.printStackTrace();
						System.out.println(e1);
					}
						
			         case 3:System.exit(0);
						}

						 
						 
						 }
			         }
				}

	private static Customer populateCustomerDetails() {
		Customer customer = new Customer();
		System.out.println("enter customername");
		customer.setCustomerName(sc.next());
		System.out.println("enter phno");
		customer.setPhno(sc.next());
		System.out.println("enter emailid");
		customer.setEmailid(sc.next());
		System.out.println("enter address");
		customer.setAddress(sc.next());
bookServiceImpl=new BookServiceImpl();
		
		try 
		{
			bookServiceImpl.validateCustomer(customer);
			return customer;
		}
		catch(BookException bookException)
		{
			
			System.err.println("invalid data");
			System.err.println(bookException.getMessage()+"\n try again");
			System.exit(0);
			bookException.printStackTrace();
		}
		
		
		return customer;
	}

	private static Book populateBookDetails() {
		Book book=new Book();
		System.out.println("enter bookname");
		book.setBookName(sc.next());
		
		System.out.println("enter author name");
		book.setAuthorName(sc.next());
		
		
		System.out.println("enter book price");
		//int bookPrice=sc.nextInt();
		
		try {
			
			
			book.setBookPrice(sc.nextInt());
			}
			catch(InputMismatchException ime)
			{
				sc.nextLine();
				System.out.println("please enter a numeric value for price,try again");
			}
		bookServiceImpl=new BookServiceImpl();
		
		try 
		{
			bookServiceImpl.validateBook(book);
			return book;
		}
		catch(BookException bookException)
		{
			
			System.err.println("invalid data");
			System.err.println(bookException.getMessage()+"\n try again");
			System.exit(0);
			bookException.printStackTrace();
		}
		
return book;
		
	}
/*	private static int calculatePrice(String[] arr) throws ClassNotFoundException, IOException, SQLException
	{
		int bookPrice=null;
		BookServiceImpl bookService = new BookServiceImpl();
		bookPrice = bookService.calPrice(arr);
		
		return bookPrice;
		
	}*/

}
