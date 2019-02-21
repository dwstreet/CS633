package application;

public class User {

	private String userName;
	private String password;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(this == o) return true;
		
		return this.userName.equals( ((User)o).userName ); // usernames are assumed unique
	}
	
	@Override
	public int hashCode() {
		
		int hashSeed = 7;
		
		hashSeed *= userName.hashCode();
		hashSeed -= 13;
		
		return hashSeed;
	}
	
	@Override
	public String toString() {
		return userName;
	}
}
