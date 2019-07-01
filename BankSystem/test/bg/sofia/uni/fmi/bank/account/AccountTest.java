package bg.sofia.uni.fmi.bank.account;

import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_AMOUNT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_BALANCE;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_IBAN;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OVERDRAFT;
import static bg.sofia.uni.fmi.bank.BankConstants.DEFAULT_OWNER_ID;
import static bg.sofia.uni.fmi.bank.BankConstants.DOUBLE_DELTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	private Account account;
	
	@Before
	public void setup() {
		account = new PrivilegeAccount(DEFAULT_IBAN, DEFAULT_OWNER_ID, DEFAULT_BALANCE, DEFAULT_OVERDRAFT);
	}
	
	@Test
	public void testChangeBalanceIncrease() {
		double amountToIncreaseWith = DEFAULT_AMOUNT;
		account.changeBalance(amountToIncreaseWith, true);
		double expectedBalance = DEFAULT_AMOUNT;
		double actualBalance = account.getBalance();
		assertEquals(expectedBalance, actualBalance, DOUBLE_DELTA);
	}
	
	@Test
	public void testChangeBalanceDecrease() {
		double amountToIncreaseWith = DEFAULT_AMOUNT;
		account.changeBalance(amountToIncreaseWith, false);
		double expectedBalance = -DEFAULT_AMOUNT;
		double actualBalance = account.getBalance();
		assertEquals(expectedBalance, actualBalance, DOUBLE_DELTA);
	}
	
	@Test
	public void testIsPossibleToDecreasePositive() {
		double amountToDecrease = 0.0;
		boolean actualPossibleToDecrease = account.isPossibleToDecreaseBalance(amountToDecrease);
		assertTrue(actualPossibleToDecrease);
	}

	@Test
	public void testIsPossibleToDecreaseNegative() {
		double amountToDecrease = DEFAULT_AMOUNT;
		boolean actualPossibleToDecrease = account.isPossibleToDecreaseBalance(amountToDecrease);
		assertFalse(actualPossibleToDecrease);
	}
}