package com.book.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.bean.Book;
import com.book.bean.Customer;
import com.book.exception.BookException;
import com.book.pl.BookMain;
import com.book.util.DBConnection;


public class BookDaoImpl implements BookDao{

	@Override
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		ResultSet resultSet=null;
		String bookId=null;
		int queryResult=0;
		try
		{
		
		 pst=con.prepareStatement("insert into book_detail values(bid_se.nextval,?,?,?)");
		pst.setString(1,book.getBookName());
		pst.setString(2,book.getAuthorName());
		pst.setInt(3,book.getBookPrice());
		pst.executeUpdate();
		//System.out.println("inserted");
		Statement st=con.createStatement();
		
		resultSet=st.executeQuery("select MAX(bid) from book_detail");
		while(resultSet.next())
		{
			bookId=resultSet.getString(1);
		}
		
		return bookId;
	}catch(SQLException sql)
	{
		System.out.println(sql);
	}
	
		return bookId;
	}

	@Override
	public Book viewBookDetails(String bookName) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection connection=DBConnection.getConnection();
		ResultSet resultSet=null;
		PreparedStatement pst=null;
		Scanner sc=new Scanner(System.in);
		Book book=new Book();
		
		pst=connection.prepareStatement("select * from book_detail where bname=?");
		//bookName=sc.nextLine();
		pst.setString(1,sc.next());
		
		//Statement st=connection.createStatement();
		resultSet=pst.executeQuery();
		while(resultSet.next())
		{
			
			book.setBookId(resultSet.getString(1));
			book.setBookName(resultSet.getString(2));
			book.setAuthorName(resultSet.getString(3));
			book.setBookPrice(resultSet.getInt(4));
			return book;
		}
		return null;
	
		
}

	@Override
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		int bookCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<Book> bookList=new ArrayList<Book>();
		try
		{
			ps=con.prepareStatement("SELECT * FROM book_detail");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				Book book=new Book();
				book.setBookId(resultset.getString(1));
				book.setBookName(resultset.getString(2));
				book.setAuthorName(resultset.getString(3));
				book.setBookPrice(resultset.getInt(4));
				
				bookList.add(book);
			bookCount++;
			}			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new BookException("Error in closing db connection");

			}
		}
		
		if( bookCount == 0)
			return null;
		else
			return  bookList;
	
	}

	@Override
	public String addCustomer(Customer customer) throws ClassNotFoundException, IOException, SQLException {
		Connection connection=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		ResultSet resultSet=null;
		String customerId=null;
		int queryResult=0;
		try
		{
		pst=connection.prepareStatement("insert into customer_detail values(cid_seq.nextval,?,?,?,?)");
		pst.setString(1,customer.getCustomerName());
		pst.setString(2,customer.getPhno());
		pst.setString(3, customer.getEmailid());
		pst.setString(4,customer.getAddress());
		pst.executeUpdate();
     Statement st=connection.createStatement();
		
	resultSet=st.executeQuery("select MAX(cid) from customer_details");
		while(resultSet.next())
		{
			customerId=resultSet.getString(1);
		}
		
		return customerId;
	
	
		
		}catch(SQLException sql)
		{
			System.out.println(sql);
		}
		
			return customerId;
	
	}

	@Override
	public List<Book> calPrice(String[] arr) throws ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		
	    PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<Book> bookList=new ArrayList<Book>();
		try
		{
			int sum=0;
			for(int i=0;i<=arr.length;i++) {
				
			ps=con.prepareStatement("SELECT * FROM book_detail");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				Book book=new Book();
				book.setBookId(resultset.getString(1));
				book.setAuthorName(resultset.getString(3));
				book.setBookName(resultset.getString(2));
				sum = sum + Integer.parseInt(resultset.getString(4));
				//int j=book.setBookPrice(resultset.getInt(4));
				bookList.add(book);
				
			}
			System.out.println("sum of books is: "+sum);
		//	return bookList;
		}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			
		}
		
		return bookList;
	}
	@Override
	public String deleteBook(Book book) throws ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		Scanner sc=new Scanner(System.in);
        ResultSet resultSet=null;
		String bookId=null;
		String bookName=null;
		int queryResult=0;
		try
		{
		
		 pst=con.prepareStatement("delete from book_detail where bname=?");
		//pst.setString(1,book.getBookName());
		 pst.setString(1,sc.next());
		pst.executeUpdate();
       //String bookName =null;
		
		//resultSet=pst.executeQuery("select max(bid) from book_detail");
           Statement st=con.createStatement();
			resultSet=st.executeQuery("select max(bid) from book_detail");
			while(resultSet.next())
			{
				
				//book.setBookId(resultSet.getString(1));
				//book.setBookName(resultSet.getString(2));
				//book.setAuthorName(resultSet.getString(3));
				//book.setBookPrice(resultSet.getInt(4));
				bookId=resultSet.getString(1);
			
			}
			return bookId;
			
	

			
			
		//}
		//return book;
		
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
			sql.printStackTrace();
		}
		
		
			return bookId;
		}

	@Override
	public Book updateDetails(int bookPrice, String bookName) throws BookException, ClassNotFoundException, IOException, SQLException {

		Connection connection = DBConnection.getConnection();
		ResultSet resultset = null;
		Statement st = connection.createStatement();
		PreparedStatement pst = null;
		Book book = null;
		try {
			pst = connection.prepareStatement("update  book_detail  SET bprice=? where bname= ?");
			pst.setInt(1, bookPrice);
			pst.setString(2,bookName);
			
			int i = pst.executeUpdate();
			System.out.println(bookName);
			resultset = st.executeQuery("select * from book_detail where bname='" + bookName + "'");

			while (resultset.next()) {
				book = new Book();
				book.setBookName(resultset.getString(1));
				book.setBookPrice(resultset.getInt(2));
				return book;

			}

		} catch (SQLException e) {
			 System.out.println(e);
			//throw new BookException("Error in closing db connection");

		}/* finally {
			try {
				resultset.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// System.out.println(e);
				throw new BookException("Error in closing db connection");

			}
		}*/
	
		
				return null;
	}


	
}
	
	



