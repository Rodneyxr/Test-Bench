package core;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

	private static final Scanner INPUT = new Scanner(System.in);
	private static final Random rand = new Random();
	private static final int EMPTY = 0, CROSS = 1, NAUGHT = 2;

	public static void main(String[] args) {
		int dim = getInt("Enter dim [3,7]: ");
		int[] board = createBoard(dim);
		int turn = CROSS;
		int counter = 0;

		while (!gameover(board)) {
			if (turn == CROSS) {
				board[makeMove(board, -1)] = CROSS; // computer move
				turn = NAUGHT;
			} else {
				board[makeMove(board, 0)] = NAUGHT; // player move
				turn = CROSS;
			}
			printBoard(board);
			counter++;
		}
		if (counter < board.length)
			System.out.printf("You %s!\n", turn == CROSS ? "WIN" : "LOSE");
		else
			System.out.println("TIE GAME!");
		printBoard(board);
	}

	private static boolean gameover(int[] board) {
		int dim = (int) Math.sqrt(board.length); // sqrt of board will be the
													// dim size ex. (if
													// board.length = 9 then dim
													// = 3)
		int test; // last move visited
		boolean isOver; // variable used as a switch assuming game will be over

		/* CHECK ROWS */
		for (int x = 0; x < dim * (dim - 1); x += dim) { // loop through each
															// row
			isOver = true; // will assume game is over
			if (board[x] != EMPTY) { // make sure first term in row is occupied
				test = board[x]; // grab first term, used to compare with rest
									// of row
				for (int y = x; y < x + dim; y++)
					// check if all terms in row are equal
					if (test != board[y]) { // is this term equal to the first
											// term in this row?
						isOver = false; // this row will not end the game
						break; // stop checking rest of row
					}
			} else
				isOver = false; // if first term is EMPTY then game is not over
			if (isOver) // game will be over if matching row is found
				return true; // :)
		}

		/* CHECK COLUMNS */
		for (int x = 0; x < dim; x++) { // loop through each column
			isOver = true; // will assume game is over
			if (board[x] != EMPTY) { // make sure first term in column is
										// occupied
				test = board[x]; // grab first term, used to compare with rest
									// of column
				for (int y = x; y <= (dim * (dim - 1)) + x; y += dim)
					// check all terms in column
					if (test != board[y]) { // is this term equal to the first
											// term in this column?
						isOver = false; // this column will not end the game
						break; // stop checking rest of row
					}
			} else
				isOver = false; // if first term is EMPTY then game is not over
			if (isOver) // game will be over if matching column is found
				return true; // :)
		}

		/* CHECK DIAGONALS */
		isOver = true; // assume
		if (board[0] != EMPTY) { // check first diagonal
			test = board[0]; // grab first term, used to compare with rest of
								// diagonal
			for (int x = 0; x <= board.length; x += dim + 1)
				// loop through each term in diagonal
				if (test != board[x]) { // is this term equal to the first term
										// in this diagonal?
					isOver = false; // this diagonal will not end the game
					break; // stop checking rest of diagonal
				}
		} else
			isOver = false; // if first term is EMPTY then game is not over
		if (isOver) // game will be over if matching diagonal is found
			return true; // :)

		isOver = true; // assume
		if (board[dim - 1] != EMPTY) { // check second diagonal
			test = board[dim - 1]; // set test to first term in diagonal
			for (int x = dim - 1; x <= dim * (dim - 1); x += dim - 1)
				// loop through each term in diagonal
				if (test != board[x]) { // is this term equal to the first term
										// in this diagonal?
					isOver = false; // this diagonal will not end the game
					break; // stop checking rest of diagonal
				}
		} else
			isOver = false; // if first term is EMPTY then game is not over
		if (isOver) // game will be over if matching diagonal is found
			return true; // :)

		/* CHECK IF BOARD IS FULL */
		isOver = true; // assume
		for (int x = 0; x < board.length; x++)
			// loop through entire board
			if (board[x] == 0) { // if a term is found EMPTY then the board is
									// not full
				isOver = false; // therefore game is not over
				break; // no used checking rest of board
			}
		if (isOver) // game will be over if board is full
			return true; // :)

		return false; // all test failed so game is not over
	}

	private static int[] createBoard(int dim) {
		int[] board = new int[dim * dim];
		for (int x = 0; x < dim * dim; x++)
			board[x] = EMPTY;
		return board;
	}

	// Pass in -1 for computer move
	private static int makeMove(int[] board, int m) {
		if (m != -1) {
			m = getInt("move: ");
			while (true) {
				if (board[m] == EMPTY)
					return m;
				m = getInt("> move: ");
			}
		} else
			while (true) {
				m = rand.nextInt(board.length);
				if (board[m] == EMPTY) {
					System.out.println("--> cpu: " + m);
					return m;
				}
			}
	}

	private static int getInt(String prompt) {
		int i = 0;
		System.out.print(prompt);
		i = INPUT.nextInt();
		return i;
	}

	private static void printBoard(int[] board) {
		int counter = 0;
		System.out.println("-----------------------------------------------------------------");
		for (int x = 0; x < Math.sqrt(board.length); x++) {
			System.out.print("[");
			for (int y = 0; y < Math.sqrt(board.length); y++) {
				System.out
						.print("\t" + (board[counter] == EMPTY ? counter : (board[counter] == CROSS ? ">X<" : ">O<")));
				counter++;
			}
			System.out.println("\t]");
		}
		System.out.println("-----------------------------------------------------------------\n");
	}

}