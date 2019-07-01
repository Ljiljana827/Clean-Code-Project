package bg.sofia.uni.fmi.bank;

import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_AMOUNT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_BALANCE;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_IBAN;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OVERDRAFT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OWNER_ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bg.sofia.uni.fmi.bank.account.Account;
import bg.sofia.uni.fmi.bank.account.PrivilegeAccount;
import bg.sofia.uni.fmi.bank.customer.Customer;

public class BankTest {
	private Bank bank;
	private Account account;
	
	@Before
	public void setup() {
		account = new PrivilegeAccount(DEFAULT_IBAN, DEFAULT_OWNER_ID, DEFAULT_BALANCE, DEFAULT_OVERDRAFT);
		bank = new Bank("", "", new ArrayList<Customer>(), new ArrayList<Account>());
	}
	
	@Test
	public void testAddCustomers() {
		Customer customer = new Customer(DEFAULT_OWNER_ID, "", "");
		boolean addedCustomer = bank.addCustomer(customer);
		assertTrue(addedCustomer);
	}
	
	@Test
	public void testDeleteCustomer() {
		boolean deletedCustomer = bank.deleteCustomer(DEFAULT_OWNER_ID);
		assertFalse(deletedCustomer);
	}
	
	@Test
	public void testaddAccountOrCreateCustomerIfAccountNotExist() {
		Customer customer = new Customer(DEFAULT_OWNER_ID, "", "");
		boolean added = bank.addAccountOrCreateCustomerIfAccountNotExist(account, customer);
		assertTrue(added);
	}
	
	@Test
	public void testDeleteAccount() {
		boolean deleted = bank.deleteAccount(DEFAULT_IBAN);
		assertFalse(deleted);
	}
	
	@Test
	public void testTransfer() {
		boolean transfered = bank.transfer(DEFAULT_IBAN, 1, DEFAULT_AMOUNT);
		assertFalse(transfered);
	}

}