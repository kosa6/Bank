package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Bank {
	
	private static final Logger LOGGER = Logger.getLogger( Bank.class.getName() );
	
	public List<Person> persons;
	public List<Account> accounts;
	public List<Transaction> transactions;
	
	public Bank(){
		this.persons = new ArrayList<Person>();
		this.accounts = new ArrayList<Account>();
		this.transactions = new ArrayList<Transaction>();
	}
	public void addCustomer(Person person){
		try {
			if(person != null) {
				persons.add(person);
			}else {
				LOGGER.log( Level.INFO, "That person is null");
			}
		} catch(NullPointerException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
	}
	public void creatAccount(Person person) {
		try {
			if(checkIfPersonIsCustomer(person) == false) {
				addCustomer(person);
			}
			Account currAccount = new Account(person, this);
			person.addAccount(currAccount);
			this.accounts.add(currAccount);
		} catch(IllegalArgumentException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		} catch(NullPointerException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		} catch(ExceptionInInitializerError e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		} catch(NoClassDefFoundError e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
	}
	private boolean checkIfPersonIsCustomer(Person person) {
		for(int i=0; i<this.persons.size(); i++) {
			if(this.persons.get(i).equals(person)) {
				return true;
			}
		}
		return false;
	}
}
