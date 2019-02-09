package application;

public class Reservation {

	private String partyName;
	private int partyNumber;

	public Reservation(String partyName, int partyNumber) {
		this.partyName = partyName;
		this.partyNumber = partyNumber;
	}

	public String getPartyName() {
		return partyName;
	}

	public int getPartyNumber() {
		return partyNumber;
	}
}
