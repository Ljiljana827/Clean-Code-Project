package bg.sofia.uni.fmi.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bg.sofia.uni.fmi.account.factory.AccountFactory;
import bg.sofia.uni.fmi.bank.account.Account;
import bg.sofia.uni.fmi.bank.customer.Customer;

public class BankMenu {

	public static void main(String[] args) {
		List<Customer> customers = new ArrayList<Customer>();
		List<Account> accounts = new ArrayList<Account>();

		Bank bank = new Bank("DSK", "ul. Business park Sofia 8", customers, accounts);
		addCustomersToBank(bank);
		addAccountsToBank(bank);

		printAvailableOptions();

		try (Scanner consoleInput = new Scanner(System.in)) {

			System.out.println("Choose option: ");
			int optionNumber = consoleInput.nextInt();

			while (optionNumber != 12) {
				switch (optionNumber) {
				case 1:
					bank.listCustomers();
					break;
				case 2:
					Customer customer = new Customer(3, "Lily", "Amsterdam");
					bank.addCustomer(customer);
					break;
				case 3:
					bank.deleteCustomer(2);
					break;
				case 4:
					bank.listAccount();
					break;
				case 5:
					bank.listCustomerAccount(1);
					break;
				case 6:
					Account savingsAccount = AccountFactory.createAccount("Savings Account", 456, 2, 50, 12);
					Customer customer2 = new Customer(3, "Diane", "Budapest");
					bank.addAccountOrCreateCustomerIfAccountNotExist(savingsAccount, customer2);
					break;
				case 7:
					bank.deleteAccount(789);
					break;
				case 8:
					bank.callWithdraw();
					break;
				case 9:
					bank.callDeposit();
					break;
				case 10:
					bank.transfer(123, 456, 30);
					break;
				case 11:
					bank.displayBankInformation();
					break;
				}

				printAvailableOptions();

				System.out.println("Choose option: ");
				optionNumber = consoleInput.nextInt();
			}

		}

	}

	private static void addCustomersToBank(Bank bank) {
		Customer customer1 = new Customer(1, "Maria", "Belgrade");
		Customer customer2 = new Customer(2, "Victoria", "Sofia");
		Customer customer3 = new Customer(3, "Diane", "Budapest");
		bank.addCustomer(customer1);
		bank.addCustomer(customer2);
		bank.addCustomer(customer3);
	}

	private static void addAccountsToBank(Bank bank) {
		Account savingsAccount = AccountFactory.createAccount("Savings Account", 456, 2, 50, 12);
		Account privilegeAccount = AccountFactory.createAccount("Privilege Account", 789, 2, 78, 33);
		Account currentAccount = AccountFactory.createAccount("Current Account", 123, 1, 39, 0);

		Customer customer1 = new Customer(1, "Maria", "Belgrade");
		Customer customer2 = new Customer(2, "Victoria", "Sofia");
		Customer customer3 = new Customer(3, "Diane", "Budapest");

		bank.addAccountOrCreateCustomerIfAccountNotExist(savingsAccount, customer1);
		bank.addAccountOrCreateCustomerIfAccountNotExist(privilegeAccount, customer2);
		bank.addAccountOrCreateCustomerIfAccountNotExist(currentAccount, customer3);
	}

	private static void printAvailableOptions() {
		System.out.println("CHOOSE AN OPTION ");
		System.out.println("1 - List customers ");
		System.out.println("2 - Add new customer ");
		System.out.println("3 - Delete customer ");
		System.out.println("4 - List all accounts ");
		System.out.println("5 - List customer accounts ");
		System.out.println("6 - Add new account ");
		System.out.println("7 - Delete account ");
		System.out.println("8 - Withdraw from account ");
		System.out.println("9 - Deposit to account ");
		System.out.println("10 - Transfer ");
		System.out.println("11 - Display info for the bank ");
		System.out.println("12 - Quit ");
	}

}
