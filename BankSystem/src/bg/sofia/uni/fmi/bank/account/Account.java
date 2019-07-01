package bg.sofia.uni.fmi.bank.account;

import java.util.Objects;

public abstract class Account {
	private int iBan;
	private int ownerId;
	private double amount;

	public Account() {
		iBan = 0;
		ownerId = 0;
		amount = 0;
	}

	public Account(int iBan, int ownerId, double amount) {
		this.iBan = iBan;
		this.ownerId = ownerId;
		this.amount = amount;
	}

	public abstract double deposit(double deposit);

	public abstract boolean withdraw(double withdraw);

	public void display() {
		System.out.println("IBAN: " + this.getIBan());
		System.out.println("Owner ID: " + this.getOwnerId());
		System.out.println("Balance: " + this.getBalance());
	}

	public int getIBan() {
		return iBan;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public double getBalance() {
		return amount;
	}

	public void changeBalance(double amount, boolean increase) {
		this.amount = increase ? this.amount + amount : this.amount - amount;
	}

	public boolean isPossibleToDecreaseBalance(double amount) {
		if (this.amount < amount) {
			System.out.println("Not enough money in the account!");
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && iBan == other.iBan
				&& ownerId == other.ownerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, iBan, ownerId);
	}
}
