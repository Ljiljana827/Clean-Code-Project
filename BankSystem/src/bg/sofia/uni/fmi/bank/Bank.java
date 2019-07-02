package bg.sofia.uni.fmi.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bg.sofia.uni.fmi.bank.account.Account;
import bg.sofia.uni.fmi.bank.customer.Customer;

public class Bank {
	private String name;
	private String address;
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Account> accounts = new ArrayList<Account>();

	public Bank(String name, String address, List<Customer> customers, List<Account> accounts) {
		this.name = name;
		this.address = address;
		this.customers = customers;
		this.accounts = accounts;
	}

	/**
	 * If there is no customer with customerID, it creates a new customer and adds
	 * it to the user list
	 * 
	 * @param customer
	 * @return true if customer do not exist and is added to list otherwise return
	 *         false
	 */
	public boolean addCustomer(Customer customer) {
		if (!customers.contains(customer)) {
			customers.add(customer);
			return true;
		}
		return false;
	}

	/**
	 * Deletes a customer with the customerID from the user list, as well as all its
	 * accounts
	 * 
	 * @param customerId
	 * @return true if customer is removed from list, and if all its accounts are
	 *         removed
	 */
	public boolean deleteCustomer(int customerId) {
		boolean deletedCustomer = false;
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				customers.remove(customer);
				deletedCustomer = true;
			}
		}

		boolean deletedAccount = false;
		for (Account account : accounts) {
			if (account.getOwnerId() == customerId) {
				accounts.remove(account);
				deletedAccount = true;
			}
		}

		return deletedCustomer && deletedAccount;
	}

	/**
	 * Display a list of customers with all information about customerID, name of
	 * customer and its address
	 */
	public void listCustomers() {
		System.out.println();
		System.out.println(" >>>> LIST OF CUSTOMERS <<<< ");

		for (Customer customer : customers) {
			customer.display();
		}
	}

	/**
	 * Display all accounts of customer with given customerID
	 * 
	 * @param customerId
	 */
	public void listCustomerAccount(int customerId) {
		System.out.println();
		System.out.println("ACCOUNTS OF CUSTOMER WITH ID " + customerId);

		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				customer.display();
			}
		}

		for (Account account : accounts) {
			if (account.getOwnerId() == customerId) {
				account.display();
			}
		}
	}

	/**
	 * If account do not exist, but customer with appropriate ID exist, added it to
	 * account list, otherwise create a new customer and added it to list of
	 * customers
	 * 
	 * @param account
	 * @param customer
	 * @return true if account do not exist, otherwise return false
	 */
	public boolean addAccountOrCreateCustomerIfAccountNotExist(Account account, Customer customer) {
		if (!accounts.contains(account)) {
			accounts.add(account);
			return true;
		} else {
			customers.add(customer);
			return false;
		}

	}

	/**
	 * Remove a account with given iBan from list
	 * 
	 * @param iBan
	 * @return true if account is removed, otherwise return false
	 */
	public boolean deleteAccount(int iBan) {
		for (Account account : accounts) {
			if (account.getIBan() == iBan) {
				accounts.remove(account);
				return true;
			}
		}
		return false;
	}

	/**
	 * Display a information for every account in list
	 */
	public void listAccount() {
		System.out.println();
		System.out.println(">>>> LIST OF ACCOUNTS <<<< ");

		int ordinalOfAccount = 1;
		for (Account account : accounts) {

			System.out.println(ordinalOfAccount + " account -> ");
			System.out.println("Balance: " + account.getBalance());
			System.out.println("IBAN: " + account.getIBan());
			System.out.println("Owner ID: " + account.getOwnerId());
			ordinalOfAccount++;
		}
	}

	/**
	 * Performs a bank transfer amount from a bank account with an identifier
	 * fromIBAN to a bank account with a toIBAN identifier, if possible
	 * 
	 * @param fromIBan
	 * @param toIBan
	 * @param amount
	 * @return true if transfer is done, otherwise return false
	 */
	public boolean transfer(int fromIBan, int toIBan, double amount) {
		boolean withdrew = false;
		boolean deposited = false;
		for (Account account : accounts) {
			if (account.getIBan() == fromIBan) {
				account.withdraw(amount);
				withdrew = true;
			}

			if (account.getIBan() == toIBan) {
				account.deposit(amount);
				deposited = true;
			}
		}
		return withdrew && deposited;
	}

	/**
	 * Puts the entered withdraw to an account with entered iBan
	 */
	public void callWithdraw() {
		try (Scanner consoleInput = new Scanner(System.in)) {

			System.out.println("Enter account iban -> ");
			int iBan = consoleInput.nextInt();
			System.out.println("Enter withdraw ->");
			int withdrow = consoleInput.nextInt();

			for (Account account : accounts) {
				if (account.getIBan() == iBan) {
					account.withdraw(withdrow);
				}
			}
		}
	}

	/**
	 * Puts the entered deposit to an account with entered iBan
	 */
	public void callDeposit() {
		try (Scanner consoleInput = new Scanner(System.in)) {

			System.out.println("Enter account iban -> ");
			int iBan = consoleInput.nextInt();
			System.out.println("Enter deposit ->");
			int deposit = consoleInput.nextInt();

			for (Account account : accounts) {
				if (account.getIBan() == iBan) {
					account.deposit(deposit);
				}
			}
		}

	}

	/**
	 * Display a information for name of bank, its address, information about number
	 * of customers and number of accounts
	 */
	public void displayBankInformation() {
		int accountsLength = accounts.size();
		int customersLength = customers.size();

		System.out.println();
		System.out.println(">>>> INFORMATION ABOUT BANK <<<<");
		System.out.println("Bank name: " + name);
		System.out.println("Bank address: " + address);
		System.out.println("Available accounts in bank: " + accountsLength);
		System.out.println("Customers in bank: " + customersLength);
	}
}
