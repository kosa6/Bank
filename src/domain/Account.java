package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Account {
	
	private static final Logger LOGGER = Logger.getLogger( Account.class.getName() );
	
	public static Account NOT_FOUND = new Account(null, null);
	
	private Bank bank;
	private static int accountNumberSeed = 10;
	private Person accountUser;
	private double balance;
	private String accountNumber;
	public List<Transaction> transactions;
	Account(Person accountUser, Bank bank){
			this.bank = bank;
			this.accountUser = accountUser;
			this.balance = 0;
			this.transactions = new ArrayList<Transaction>();
			generateNumber();
	}
	public String getAccountNUmber() {
		return this.accountNumber;
	}
	public double getBalance() {
		return this.balance;
	}
	private void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	boolean addMoney(double amount) {
		if(amount > 0) {
			this.balance += amount;
			return true;
		}else {
			LOGGER.log(Level.INFO, "Account not exist");
			return false;
		}
	}
	boolean lessMoney(double amount) {
		if(this.balance > amount) {
			this.balance -= amount;
			return true;
		}
		else {
			LOGGER.log(Level.INFO, "You don't have enought money");
			return false;
		}
	}
	private void generateNumber() {
		this.accountNumber = ""; 
		for(int i=0; i<4; i++) {
			Random rand = new Random();
			this.accountNumber += (rand.nextInt(8999) + 1000);
		}
		this.accountNumber+=Account.accountNumberSeed;
	}
	public Person getAccountUser() {
		return this.accountUser;
	}
	public void withdrawal(double amount, String description) throws NullPointerException {
		if(chectIfAccountNumberIsCorrect(accountNumber)) {
			try {
				if(this.lessMoney(amount)) {
					this.addTrasaction(this, TypeOfTransaction.withdrawal, description);
				}
			}catch(NullPointerException e) {
				LOGGER.log( Level.SEVERE, e.toString(), e );
			}
		}
		else {
			LOGGER.log(Level.INFO, "Account number not correct");
		}
	}
	public void deposit(String accountNumber, double amount, String description) throws NullPointerException {
		LOGGER.log(Level.INFO, "Account number not correct or account from witch you wannt to deposit money not exsist");
			if(chectIfAccountNumberIsCorrect(accountNumber)) {
					Account temp = chceckIfAccountExist(accountNumber);
					if(temp.addMoney(amount)) {
						this.addTrasaction(temp, TypeOfTransaction.deposit, description);
					}
			}
			else {
				LOGGER.log(Level.INFO, "Account number not correct or account from witch you wannt to deposit money not exsist");
			}
	}
	private boolean chectIfAccountNumberIsCorrect(String accountNumber) {
		if(accountNumber.length() == 18 && isNumeric(accountNumber) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	private Account chceckIfAccountExist(String accountNumber) {
		for(int i=0; i<bank.accounts.size(); i++) {
			if(this.bank.accounts.get(i).getAccountNUmber().equals(accountNumber)){
				return this.bank.accounts.get(i);
			}
		}
		return null;
	}
	private void addTrasaction(Account account, TypeOfTransaction type,String titleOfTransaction) {
		Transaction transaction = new Transaction(account.getAccountUser(),type,titleOfTransaction);
		saveTransactionToFile(transaction);
		this.transactions.add(transaction);
		account.addTransaction(transaction);
		account.getAccountUser().addNewTransaction(transaction);
		
	}
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Long.parseLong(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	private void saveTransactionToFile(Transaction t) {
		try (FileWriter  fileReader = new FileWriter ("bank.txt", true); BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
				PrintWriter printWriter = new PrintWriter(bufferedWriter);)
		{
			printWriter.write("Transaction"+System.lineSeparator() + "Date: "+ t.getDateOfTransactioin() +System.lineSeparator() + "Person: " + 
		t.getPersonWhoDidTransaction().getFirstName() + System.lineSeparator() +"Title od transaction: " + t.getTitleOfTransaction() + System.lineSeparator() );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
