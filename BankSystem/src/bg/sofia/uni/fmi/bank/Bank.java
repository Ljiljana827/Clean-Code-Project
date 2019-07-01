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

	public boolean addCustomer(Customer customer) {
		if (!customers.contains(customer)) {
			customers.add(customer);
			return true;
		}
		return false;
	}

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

	public void listCustomers() {
		System.out.println();
		System.out.println(" >>>> LIST OF CUSTOMERS <<<< ");

		for (Customer customer : customers) {
			customer.display();
		}
	}

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

	public boolean addAccountOrCreateCustomerIfAccountNotExist(Account account, Customer customer) {
		if (!accounts.contains(account)) {
			accounts.add(account);
			return true;
		} else {
			customers.add(customer);
			return false;
		}

	}

	public boolean deleteAccount(int iBan) {
		for (Account account : accounts) {
			if (account.getIBan() == iBan) {
				accounts.remove(account);
				return true;
			}
		}
		return false;
	}

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

	public void callWithdraw() {
		try (Scanner consoleInput = new Scanner(System.in)) {

			System.out.println("Enter account iban -> ");
			int iBan = consoleInput.nextInt();
			System.out.println("Enter deposit ->");
			int withdrow = consoleInput.nextInt();

			for (Account account : accounts) {
				if (account.getIBan() == iBan) {
					account.withdraw(withdrow);
				}
			}
		}
	}

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
