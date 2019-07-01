package bg.sofia.uni.fmi.bank.account;

import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_AMOUNT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_BALANCE;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_IBAN;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OWNER_ID;
import static bg.sofia.uni.fmi.bank.BankConstants.DOUBLE_DELTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CurrentAccountTest {

	private Account account;
	
	@Before
	public void setup() {
		account = new CurrentAccount(DEFAULT_IBAN, DEFAULT_OWNER_ID, DEFAULT_BALANCE);
	}
	
	@Test
	public void testDeposit() {
		double balanceToChange = DEFAULT_AMOUNT;
		double actualChangedBalance = account.deposit(balanceToChange);
		double expectedChangedBalance = DEFAULT_AMOUNT;
		assertEquals(expectedChangedBalance, actualChangedBalance, DOUBLE_DELTA);
	}
	
	@Test
	public void testWithdawPositive() {
		double withdrawAmount = -DEFAULT_AMOUNT;
		boolean withdrew = account.withdraw(withdrawAmount);
		double actualBalance = account.getBalance();
		double expectedBalance = DEFAULT_AMOUNT;
		assertTrue(withdrew);
		assertEquals(expectedBalance, actualBalance, DOUBLE_DELTA);
	}
	
	@Test
	public void testWithdrawNegative() {
		double withdrawAmount = DEFAULT_AMOUNT;
		boolean withdrew = account.withdraw(withdrawAmount);
		double actualBalance = account.getBalance();
		double expectedBalance = 0.0;
		assertFalse(withdrew);
		assertEquals(expectedBalance, actualBalance, DOUBLE_DELTA);
	}
}