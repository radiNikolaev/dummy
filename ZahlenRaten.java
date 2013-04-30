/**
 * In this game, I will first choose a secret random number, and one of us (either you or me) must guess it iteratively... <br/>
 * All I will reveal for a guess is whether the guess is higher or lower than the secret random number in my mind.<br/>
 * You may use everything that is provided to you in this class, but<br/>
 * DO NOT CHANGE ANYTHING except for the bodies of the methods marked with "ToDo"!
 * 
 * @author Dr.-Ing. Norbert Oster
 * @version 2.0 (build 20130408)
 */
public class ZahlenRaten {
	/**
	 * The smallest possible number I may choose.
	 */
	protected final int MIN;

	/**
	 * The greatest possible number I may think of.
	 */
	protected final int MAX;

	/**
	 * Just for statistics...<br/>
	 */
	protected long numberOfGuesses = 0;

	/**
	 * The secret number between {@link #MIN} and {@link #MAX} (both inclusive) to be guessed.
	 */
	protected final int toBeGuessed;

	/**
	 * Let the games begin (here)!<br/>
	 * There is no need for you to change this method in any way (but you should test your code with different min/max-values...)!<br/>
	 * The secret number to be guessed is chosen between {@link #MIN} and {@link #MAX} (both inclusive).<br/>
	 * @see <a href="http://download-llnw.oracle.com/javase/6/docs/api/java/lang/Math.html#random()">Math.random()</a>
	 * @param args If you don't provide one (whatever), I'll play by myself - otherwise you can puzzle it out...
	 */
	public static void main(String[] args) {
		int min = 1;
		int max = 100;
		int toBeGuessed = min + ((int)(Math.random() * (max - min + 1)));
		ZahlenRaten game = new ZahlenRaten(min, max, toBeGuessed);
		if (args.length > 0) {
			game.playWithYou();
		} else {
			game.playByMyself();
		}
	}

	/**
	 * Initialises a new game.<br/>
	 */
	protected ZahlenRaten(int min, int max, int toBeGuessed) {
		this.MIN = min;
		this.MAX = max;
		this.toBeGuessed = toBeGuessed;
	}

	/**
	 * Checks whether {@code myGuess} is less than, equal or greater than the real secret number {@link #toBeGuessed} and returns the "encoded result" correspondingly.
	 * @param myGuess This is the guess to be evaluated.
	 * @return
	 * <table>
	 * <tr><td>-1</td><td>: if {@code myGuess} is less than the secret value {@link #toBeGuessed}.</td></tr>
	 * <tr><td>0</td><td>: if {@code myGuess} is exactly the the secret value <i>(yeah!)</i></td></tr>
	 * <tr><td>1</td><td>: if {@code myGuess} is greater than the secret value.</td></tr>
	 * </table>
	 */
	protected int checkGuess(int myGuess) {
		int result;
		// TODO: PUT YOUR CODE HERE
		if (myGuess == toBeGuessed){
			result = 0;
		} else if (myGuess > toBeGuessed){
			result = 1;
		} else {
			result = -1;
		}

		System.out.println("The " + ++numberOfGuesses + ". guess is \"" + myGuess + "\" and it is " + (result < 0 ? "too small." : result > 0 ? "too high." : "PERFECT!"));
		return result;
	}

	/**
	 * I'll choose the secret number {@link #toBeGuessed} and a human player must try to guess it.
	 */
	protected void playWithYou() {

		// TODO: PUT YOUR CODE HERE
		int tmp = inputInt();
		int direction = checkGuess(tmp);
		while (direction != 0){
			tmp = inputInt();
			direction = checkGuess(tmp);
		}
	}

	/**
	 * I'll just pretend that I don't know the value {@link #toBeGuessed} and puzzle myself over it just like a human player.<br/>
	 * I will do my best to find the secret number within as few steps as possible!
	 */
	protected void playByMyself() {

		// TODO: PUT YOUR CODE HERE
		
		int tmp = (MAX-MIN)/2;
		int direction = checkGuess(tmp);
		if (direction == -1){
			while (direction == -1){
				tmp += 2;						
				direction = checkGuess(tmp);
			} 
			if (direction != 0){
				direction = checkGuess(--tmp);
			}
		} else if (direction == 1){
			while (direction == 1){
				tmp -= 2;
				direction = checkGuess(tmp);
			} 
			if (direction != 0){
				direction = checkGuess(++tmp);
			}
		} 
	}

	/**
	 * Helper infrastructure, used to read human input.
	 */
	protected int inputInt() {
		try {
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			while(true) {
				System.out.print("Tell me your guess: ");
				String inputString = reader.readLine();
				if (inputString == null) throw new java.io.EOFException();
				try {
					return Integer.parseInt(inputString);
				} catch (NumberFormatException e) {
					System.out.println("- Your input (\"" + inputString + "\") is not a valid number! Try again...");
				}
			}
		} catch (Throwable throwable) {
			System.err.println();
			System.err.println("- Sorry, it just doesn't work here with you... I'll shut down now!");
			System.exit(1);
			return 0;
		}
	}
}
