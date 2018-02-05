package main;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.*;

public class Main {
	
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank ing = new Bank();
		Person ko = null;
		try {
			ko = new Person("KOnrad", "", "6", "08", "1996");
		} catch (IllegalArgumentException | NullPointerException | ParseException e) {
			
		}
		Person dawid = null;
		try {
			dawid = new Person("Dawid", "Sowisz", "6", "08", "1996");
		} catch (IllegalArgumentException | NullPointerException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person jolo = null;
		try {
			jolo = new Person("Jolo", "Brawo", "5", "4", "2000");
		} catch (IllegalArgumentException | NullPointerException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ing.addCustomer(ko);
		ing.addCustomer(dawid);
		ing.creatAccount(jolo);
		//ing.creatAccount(ko);
		ko.getBirthDate();
		System.out.println(ko.getFirstName());
		//System.out.println(ko.accounts.get(0).getBalance());
		ko.getBirthDate();
		System.out.println(dawid.getFirstName());
		
	}
}
