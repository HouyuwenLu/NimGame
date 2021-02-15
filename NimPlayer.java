
public class NimPlayer {
	// declare the name, wins, and games as instance variables in the class NimPlayer
	private String name;
	private int wins;
	private int games;

	public NimPlayer(String name) {
		// NimPlayer constructor to initialize the instance variables 
		this.name = name;
		this.wins = 0;
		this.games = 0; 
	}
	
	public int removeStone() {
		// remove stones function that can be used in the Nimsys class
		int remove = Nimsys.scanner.nextInt();
		return remove; 
	}
	
	
	public void updateWins() {
		// update wins can be used in Nimsys class
		this.wins += 1;
	}
	
	public void updateGames() {
		// update games can be used in Nimsys class
		this.games += 1;
	}

	// use getter and setter to get and set the names, games, and wins 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}


	public int getGames() {
		return games;
	}

}
	
	
	

