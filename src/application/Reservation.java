package application;

public class Reservation {

	private String partyName;
	private int partyNumber;
	private DayTime seatTime;

	public Reservation(String partyName, int partyNumber, DayTime seatTime) {
		this.partyName = partyName;
		this.partyNumber = partyNumber;
		this.seatTime = seatTime;
	}

	public String getPartyName() {
		return partyName;
	}

	public int getPartyNumber() {
		return partyNumber;
	}
	
	public DayTime getSeatTime() {
		return seatTime;
	}
}
