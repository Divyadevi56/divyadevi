package com.cg.donor.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.donor.bean.DonorBean;
import com.cg.donor.dao.DonorDaoImpl;
import com.cg.donor.dao.IDonorDAO;
import com.cg.donor.exception.DonorException;

public class DonorServiceImpl implements IDonorService{
IDonorDAO donordao=new DonorDaoImpl();
	@Override
	public String addDonor(DonorBean donor) throws DonorException, ClassNotFoundException, IOException, SQLException {
		String donorSeq;
		donorSeq=donordao.addDonor(donor);
		return donorSeq;
	}

	@Override
	public DonorBean viewDonorDetails(String donorId) throws DonorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List retriveAll() throws DonorException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void validateDonor(DonorBean bean) throws DonorException
	{
		List<String> validationErrors=new ArrayList<String>();
		
		if(!isValidName(bean.getDonorName()))
		{
			validationErrors.add("\n Donor Name should be in Alphabets and minimum 3 characters long\n");
			
		}
		if(!(isValidAddress(bean.getAddress())))
		{
			validationErrors.add("Address should be greater than 5 characters");
			
		}
		if(!(isValidPhoneNumber(bean.getPhoneNumber())))
		{
			validationErrors.add("Phone number should be in 10 digits");
		}
		if(!(ValidAmount(bean.getDonationAmount())))
		{
			validationErrors.add("Amount should be positive number");
		}
		if(!(ValidId(bean.getDonorId())))
		{
			validationErrors.add("enter valid id");
		}
		if(!validationErrors.isEmpty())
		{
			throw new DonorException(validationErrors+"");
		}
	}

	private boolean ValidId(String donorId) {
		Pattern p=Pattern.compile("^[0-9]{1,4}");
		Matcher idMatcher=p.matcher(donorId);
		if(idMatcher.matches())
		return true;
		else
		return false;
	}

	private boolean isValidAddress(String address) {
		// TODO Auto-generated method stub
		return (address.length()>6);
	}

	private boolean ValidAmount(double donationAmount) {
		
		return donationAmount>0;
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		Pattern p=Pattern.compile("^[6-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=p.matcher(phoneNumber);
		return false;
	}

	private boolean isValidName(String donorName) {
		Pattern p=Pattern.compile("^[A-Za-z] {3,}$");
		Matcher phoneMatcher=p.matcher(donorName);
		return false;
		
		}

}
