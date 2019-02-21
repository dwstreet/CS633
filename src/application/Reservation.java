package application;

public class Reservation {

	private String partyName;
	private int partyNumber;
	private DayTime seatTime;
	private String notes;

	public Reservation(String partyName, int partyNumber, DayTime seatTime, String notes) {
		this.partyName = partyName;
		this.partyNumber = partyNumber;
		this.seatTime = seatTime;
		this.notes = notes;
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

	public String getNotes() {
		return notes;
	}

	@Override
	public boolean equals(Object o) {

		if(this == o) return true;

		Reservation otherRes = (Reservation) o;

		return this.partyName == otherRes.partyName && this.seatTime == otherRes.seatTime;
	}

	@Override
	public int hashCode() {

		int hashSeed = 421;
		hashSeed *= partyName.hashCode();
		hashSeed -= 22;

		hashSeed *= seatTime.hashCode();
		hashSeed -= 13;

		return hashSeed;
	}
}
