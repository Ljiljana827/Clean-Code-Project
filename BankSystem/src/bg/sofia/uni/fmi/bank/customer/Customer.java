package bg.sofia.uni.fmi.bank.customer;

import java.util.Objects;

public class Customer {
	private int id;
	private String name;
	private String address;

	public Customer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void display() {
		System.out.println("Customer ID: " + id);
		System.out.println("Customer name: " + name);
		System.out.println("Customer address: " + address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, name);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) object;
		return Objects.equals(address, other.address) && id == other.id && Objects.equals(name, other.name);
	}
}
