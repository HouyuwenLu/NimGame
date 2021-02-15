
import java.util.Scanner;

public class Nimsys {
	// set both private and public static varibles of the Nimsys class 
	private static char SYMBOL = '$'; 
	// scanner can be used in NimPlayer class to get input for stones removed 
	public static Scanner scanner = new Scanner(System.in);
	// commands are grouped in a array and can be added or deleted from this array 
	private static String[] commandLists = { "start", "exit", "help", "commands" }; 

	public static void main(String[] args) {
		welcome();
		commandInput();
	}

	public static void welcome() {
		// set welcome method that can be used by invocation 
		System.out.println("Welcome to Nim");
		System.out.println();
		System.out.println("Please enter a command to continue");
		System.out.println();
	}

	public static void commandInput() {
		// get command input to invoke the relative methods 
		String command = null;
		boolean operation = true;
		while (operation) {
			System.out.print(SYMBOL + " ");
			command = scanner.nextLine();
			if (command.equals("start")) {
				startGame();
			} else if (command.equals("help")) {
				help();
			} else if (command.equals("exit")) {
				exit();
				operation = false;
				break;
			} else if (command.equals("command")) {
				System.out.println();
				commandList(commandLists);
			}
		}
	}

	public static void startGame() {
		// get player1's name, player2's name
		System.out.print("\nPlease enter Player 1's name : ");
		String name1 = scanner.nextLine();
		System.out.print("Please enter Player 2's name : ");
		String name2 = scanner.nextLine();
		// set two instances to invoke methods from the NimPlayer class 
		NimPlayer player1 = new NimPlayer(name1);
		NimPlayer player2 = new NimPlayer(name2);
		NimPlayer currPlayer = null;
		String input = null;
		Boolean flag = true;
		// use while loop to play the game and if "Y" the game will continue 
		// otherwise the game will exit
		while (flag) {
			currPlayer = playGame(player1, player2); // pass two instance values into playGame method
			currPlayer.updateWins(); // invoke updateWins method from NimPlayer class
			player1.updateGames(); // invoke updateGames method from NimPlayer class
			player2.updateGames(); // invoke updateGames method from NimPlayer class
			System.out.println();
			System.out.println("Game Over\n" + currPlayer.getName() + " wins!");
			System.out.println();
			System.out.print("Do you want to play again (Y/N): ");
			input = scanner.next();
			scanner.nextLine();
			if (input.equalsIgnoreCase("Y")) {
				continue;
			} else if (input.equalsIgnoreCase("N")) {
				int player1_win = player1.getWins();
				int player2_win = player2.getWins();
				// set singular or plural based on the game number 
				String player1_games = player1_win > 1 ? " games " : " game ";
				String player2_games = player2_win > 1 ? " games " : " game ";
				// final records output of player1 and player2 by invoking the methods in NimPlayer class
				System.out.println(player1.getName() + " won " + player1_win +
					player1_games + "out of " + player1.getGames() + " played");
				System.out.println(player2.getName() + " won " + player2_win + 
					player2_games + "out of " + player2.getGames() + " played");
				break;
			} else {
				break;	
			}
		}
	}

	private static NimPlayer playGame(NimPlayer player1, NimPlayer player2) {
		// read upper bound and stones number and play the game
		System.out.print("Enter upper bound : ");
		int upperBound = scanner.nextInt();
		System.out.print("Enter initial number of stones : ");
		int totalStones = scanner.nextInt();
		System.out.println();
		NimPlayer currPlayer = player1;
		int round = 0;
		int move;
		String currName = null;
		// playing the game with while loop when stone numebr and upperbound 
		// number are valid; otherwise, the game will request reinputs 
		while (totalStones > 0) {
			currName = currPlayer.getName();
			outputStone(totalStones);
			System.out.print(currName + "'s turn. Enter stones to remove : ");
			while ((move = currPlayer.removeStone()) > Math.min(upperBound, totalStones)) {
				if (move > upperBound)
					System.out.println("Upper bound limit exceed, upper bound maximum choice is N");
				else if (move > totalStones)
					System.out.println("Invalid attempt, only M stones remaining! Try again:");
				System.out.print(currName + "'s turn. Enter stones to remove : ");
			}
			totalStones -= move;
			round += 1;

			if (round % 2 == 1) {
				currPlayer = player2;
			} else {
				currPlayer = player1;
			}
			if (totalStones == 0) {
				break;
			}
			System.out.println();
		}

		return currPlayer;

	}

	private static void outputStone(int stones) {
		// stones left number and pattern output
		System.out.print(stones + " stones left :");
		for (int i = 1; i <= stones; i++) {
			System.out.print(" *");
		}
		System.out.println();
	}

	private static void exit() {
		System.out.println("\nThank you for playing Nim");
		System.out.println();
	}

	private static void help() {
		System.out.println("Type 'commands' to list all available commands/n"
				+ "Type 'Start' to play game/nPlayer to remove the last stone loses!");
		System.out.println();
	}
	
	// use array to put all the commands as a loop  
	private static void commandList(String[] commandLists) {
		int index;
		for (index = 1; index <= commandLists.length; index++) {
			System.out.println(": " + commandLists[index]);
		}
	}
}
