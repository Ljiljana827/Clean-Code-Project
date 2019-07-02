package bg.sofia.uni.fmi.account.factory;

import bg.sofia.uni.fmi.bank.account.Account;
import bg.sofia.uni.fmi.bank.account.CurrentAccount;
import bg.sofia.uni.fmi.bank.account.PrivilegeAccount;
import bg.sofia.uni.fmi.bank.account.SavingsAccount;

public class AccountFactory {

	/**
	 * Creates an account with type given by parameter
	 * 
	 * @param accountType
	 * @param iBan
	 * @param ownerId
	 * @param amount
	 * @param typeSpecificParam
	 * @return a requested account type
	 */
	public static Account createAccount(String accountType, int iBan, int ownerId, double amount,
			double typeSpecificParam) {

		if (accountType.equals("Current Account")) {
			return new CurrentAccount(iBan, ownerId, amount);
		} else if (accountType.equals("Privilege Account")) {
			return new PrivilegeAccount(iBan, ownerId, amount, typeSpecificParam);
		} else if (accountType.equals("Savings Account")) {
			return new SavingsAccount(iBan, ownerId, amount, typeSpecificParam);
		} else {
			throw new IllegalArgumentException("Account type doesn't exist");
		}
	}

}
