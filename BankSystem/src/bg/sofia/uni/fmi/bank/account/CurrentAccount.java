package bg.sofia.uni.fmi.bank.account;

public class CurrentAccount extends Account {

	public CurrentAccount(int iBan, int ownerId, double amount) {
		super(iBan, ownerId, amount);
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
		System.out.println("----- Current Account -----");
		super.display();
	}

}
