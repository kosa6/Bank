package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

public class Person {
	
	private static final Logger LOGGER = Logger.getLogger( Person.class.getName() );
	
	public static Person NOT_FOUND = new Person();
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	public List<Account> accounts;
	public List<Transaction> transactions;
	
	public Person(String firstName, String lastName, String day, String month, String year) throws ParseException, IllegalArgumentException, NullPointerException{
		try {
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setBirthDate(day,month,year);
			this.accounts = new ArrayList<Account>();
			this.transactions = new ArrayList<Transaction>();
		} catch (ParseException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
			throw e;
		} catch (IllegalArgumentException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
			throw e;
		} catch (NullPointerException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
			throw e;
		}
	}
	private Person() {
		this.firstName = null;
		this.lastName = null;
		this.birthDate = null;
		this.accounts = Collections.emptyList();
		this.transactions = Collections.emptyList();
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public Date getBirthDate() {
		return this.birthDate;
	}
	void setFirstName(String firstName) throws IllegalArgumentException{
			if(firstName != null && !firstName.isEmpty() && isNotNumber(firstName) == true) {
				this.firstName = firstName;
			}
			else {
				throw new IllegalArgumentException("First name is empty");
			}
	}
	void setLastName(String lastName) throws IllegalArgumentException{
			if(lastName != null && !lastName.isEmpty() && isNotNumber(lastName) == true) {
				this.lastName = lastName;
			}
			else {
				throw new IllegalArgumentException("Last name is empty");
			}
	}
	private boolean isNotNumber(String s) {
		try {
			Double.parseDouble(s);
		}
		catch(NumberFormatException e) {
			return true;
		}
		return false;
	}
	void setBirthDate(String month, String day, String year) throws IllegalArgumentException, ParseException {
	    String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		int monthInt = Integer.parseInt(month);
		int dayInt = Integer.parseInt(day);
	    if(monthInt > 12 || dayInt > 31) {
	    	throw new IllegalArgumentException("Wrong day or month");
	    }
	    try {
			Date birthDate1 = format.parse(month+"/"+day+"/"+year);
			Date after = format.parse("01/01/1900");
		    if(birthDate1.before(new Date()) && birthDate1.after(after)) {
		    	  this.birthDate = birthDate1;
		    }
		    else {
		    	  throw new IllegalArgumentException("Too old or to young");
		    }
	    }catch(ParseException e) {
	    	throw e;
	    }
	}
	void addAccount(Account account) throws IllegalArgumentException {
		try {
			this.accounts.add(account);
		} catch( NullPointerException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
	}
	void addNewTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
}
