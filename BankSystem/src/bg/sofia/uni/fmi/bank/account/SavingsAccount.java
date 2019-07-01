package bg.sofia.uni.fmi.bank.account;

public class SavingsAccount extends Account {

	private double interestRate;

	public SavingsAccount(int iBan, int ownerId, double amount, double interestRate) {
		super(iBan, ownerId, amount);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;

	}

	@Override
	public double deposit(double deposit) {
		changeBalance(deposit, true);
		return this.getBalance();
	}

	@Override
	public boolean withdraw(double withdraw) {
		if (isPossibleToDecreaseBalance(withdraw)) {
			changeBalance(withdraw, false);
			return true;
		}
		return false;
	}

	@Override
	public void display() {
		System.out.println("----- Savings Account -----");
		super.display();
		System.out.println("Interest rate: " + this.getInterestRate());
	}
}
