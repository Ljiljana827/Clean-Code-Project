package bg.sofia.uni.fmi.account.factory;

import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_BALANCE;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_IBAN;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_INTEREST_RATE;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OVERDRAFT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OWNER_ID;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bg.sofia.uni.fmi.bank.account.Account;
import bg.sofia.uni.fmi.bank.account.CurrentAccount;
import bg.sofia.uni.fmi.bank.account.PrivilegeAccount;
import bg.sofia.uni.fmi.bank.account.SavingsAccount;

public class AccountFactoryTest {

	@Test
	public void testCreateAccountWithPrivileged() {
		Account actualAccountClass = AccountFactory.createAccount("Privilege Account", DEFAULT_IBAN, DEFAULT_OWNER_ID,
				DEFAULT_BALANCE, DEFAULT_OVERDRAFT);
		Class<PrivilegeAccount> expectedAccountClass = PrivilegeAccount.class;
		assertEquals(expectedAccountClass, actualAccountClass.getClass());
	}

	@Test
	public void testCreateAccountWithSaved() {
		Account actualAccountClass = AccountFactory.createAccount("Savings Account", DEFAULT_IBAN, DEFAULT_OWNER_ID,
				DEFAULT_BALANCE, DEFAULT_INTEREST_RATE);
		Class<SavingsAccount> expectedAccountClass = SavingsAccount.class;
		assertEquals(expectedAccountClass, actualAccountClass.getClass());
	}

	@Test
	public void testCreateAccountWithCurrent() {
		Account actualAccountClass = AccountFactory.createAccount("Current Account", DEFAULT_IBAN, DEFAULT_OWNER_ID,
				DEFAULT_BALANCE, DEFAULT_OVERDRAFT);
		Class<CurrentAccount> expectedAccountClass = CurrentAccount.class;
		assertEquals(expectedAccountClass, actualAccountClass.getClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountWithNonExistingType() {
		AccountFactory.createAccount("No Account", DEFAULT_IBAN, DEFAULT_OWNER_ID, DEFAULT_BALANCE, DEFAULT_OVERDRAFT);
	}
}