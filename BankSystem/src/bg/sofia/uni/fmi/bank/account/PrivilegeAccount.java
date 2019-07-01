package bg.sofia.uni.fmi.bank.account;

public class PrivilegeAccount extends Account {

	private double overdraft;

	public PrivilegeAccount(int iBan, int ownerId, double amount, double overdraft) {
		super(iBan, ownerId, amount);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	@Override
	public double deposit(double deposit) {
		changeBalance(deposit, true);
		return this.getBalance();
	}

	@Override
	public boolean withdraw(double withdraw) {
		if (this.getBalance() + this.overdraft > withdraw) {
			changeBalance(withdraw, false);
			return true;
		}
		return false;
	}

	@Override
	public void display() {
		System.out.println("----- Privilege Account -----");
		super.display();
		System.out.println("Overdraft: " + this.getOverdraft());

	}

}
