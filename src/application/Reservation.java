package application;

public class Reservation implements Comparable<Reservation> {

	private User user;
	private String partyName;
	private int partyNumber;
	private DayMonthYear dmy;
	private DayTime seatTime;
	private String notes;

	public Reservation(User user, String partyName, int partyNumber, DayMonthYear dmy, DayTime seatTime, String notes) {
		this.user = user;
		this.partyName = partyName;
		this.partyNumber = partyNumber;
		this.dmy = dmy;
		this.seatTime = seatTime;
		this.notes = notes;
	}
	
	public User getUser() {
		return user;
	}

	public String getPartyName() {
		return partyName;
	}

	public int getPartyNumber() {
		return partyNumber;
	}
	
	public DayMonthYear getDayMonthYear() {
		return dmy;
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

		return this.user.equals(otherRes.user) && this.partyName.equals(otherRes.partyName) && this.dmy.equals(otherRes.dmy) && this.seatTime.equals(otherRes.seatTime);
	}

	@Override
	public int hashCode() {

		int hashSeed = 421;
		
		hashSeed *= user.hashCode();
		hashSeed += 5;
		
		hashSeed *= partyName.hashCode();
		hashSeed -= 22;
		
		hashSeed *= dmy.hashCode();
		hashSeed += 19;

		hashSeed *= seatTime.hashCode();
		hashSeed -= 13;

		return hashSeed;
	}

	@Override
	public int compareTo(Reservation o) {

		int ret = this.dmy.compareTo(o.dmy);
		
		if(ret == 0) {
			return this.seatTime.compareTo(o.seatTime);
		}
		else {
			return ret;
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(partyName).append(", party of ").append(partyNumber).append(" at ").append(dmy.toString()).append(" ").append(seatTime.toString());
		return sb.toString();
	}

}
