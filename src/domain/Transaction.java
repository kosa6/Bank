package domain;

import java.util.Date;

class Transaction {
	
	public static Transaction NOT_FOUND = null;
	
	private Date dateOfTransactioin;
	private Person personWhoDidTransaction;
	private TypeOfTransaction typeOfTransaction;
	private String title;
	Transaction(Person person,TypeOfTransaction type,String title){
		this.dateOfTransactioin = new Date();
		this.personWhoDidTransaction = person;
		this.typeOfTransaction = type;
		this.title = title;
	}
	public Date getDateOfTransactioin() {
		return dateOfTransactioin;
	}
	public Person getPersonWhoDidTransaction() {
		return personWhoDidTransaction;
	}
	public TypeOfTransaction getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public String getTitleOfTransaction() {
		return this.title;
	}
}
