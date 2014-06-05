import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MemoryGame {	

	private static void loadScreen(int startLine, int endLine) {

		// Link the File variable to a file on the computer
		File screen = new File("screen.txt");
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(screen);
			ArrayList<String> loading = new ArrayList<>();
			// Read file
			for(int i = 0; i <= 100; i++){
				loading.add(fileReader.nextLine());
			}		
			
			for (int i = startLine; i <= endLine; i++) {
				System.out.println(loading.get(i));
				if (i < 40) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
			}
			
			// Close the resource after you've finished using it
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillWords(ArrayList<String> words) {
		
		words.add("C#"); words.add("C#"); words.add("C++"); words.add("C++");
		words.add("JAVA"); words.add("JAVA"); words.add("PHP"); words.add("PHP");
		words.add("RUBY"); words.add("RUBY"); words.add("PYTHON"); words.add("PYTHON");
		words.add("HTML"); words.add("HTML"); words.add("CSS"); words.add("CSS");	
	}
	
	public static void fillNumbers(ArrayList<String> numbers) {
		
		numbers.add("[1]"); numbers.add("[2]"); numbers.add("[3]"); numbers.add("[4]");
		numbers.add("[5]"); numbers.add("[6]"); numbers.add("[7]"); numbers.add("[8]");
		numbers.add("[9]"); numbers.add("[10]"); numbers.add("[11]"); numbers.add("[12]");
		numbers.add("[13]"); numbers.add("[14]"); numbers.add("[15]"); numbers.add("[16]");
	}

	public static void main(String[] args) {

		loadScreen(50, 90);
		loadScreen(0, 32);
		
		int row = 4;
		int col = 4;
		int counter=0;
		
		Random r = new Random();
		
		int count = 0;
		int row1, col1, row2, col2;
		int scoreCounter = 0;
		int movesCounter = 0;
		
		//create boards
		String[][] board = new String[row][col];
		String[][] boardForPrint = new String[row][col];
		String[][] boardWithNumbers = new String[row][col];
		Boolean[][] boardWithBools = new Boolean[row][col];
		
		ArrayList<String> numbers = new ArrayList<>();
		fillNumbers(numbers);
		
		ArrayList<String> words = new ArrayList<>();
		fillWords(words);
		
		//fill board with words
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int randomElement = r.nextInt(words.size());
				board[i][j]=words.get(randomElement);
				words.remove(randomElement);
			}		 
		}
		
		//fill bool board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				boardWithBools[i][j] = false;
			}		 
		}
		
		//add words again, because of remove for random
		fillWords(words);
		
		//fill board with numbers		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {				
				boardWithNumbers[i][j]=numbers.get(count);
				count++;
			}		 
		}
		
		//fill board for print with numbers before game started
		count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				
				boardForPrint[i][j]=numbers.get(count);
				count++;
			}		 
		}
		
		Scanner scan = new Scanner(System.in);
		String play = scan.nextLine();
		
		//play or quit choice
		while((!play.equals("y")) || (!play.equals("n"))) {
					
			if(play.equals("y") || play.equals("n")) {
				break;
			}
			System.out.println("Do you want to test and improve your memory skills?");
			System.out.println("Press 'y' for \"Yes\" and 'n' for \" No\"");	
			play = scan.nextLine();
		}
		
		if(play.equals("n")) {
			System.out.println("You quitted, sorry");
		}		
		
		//game start
		while((play.equals("y")) && (counter != numbers.size() / 2)) {
			
			//"clear" console
			loadScreen(50, 60);
			loadScreen(34, 43);
			loadScreen(55, 60);
					
			//print board with numbers
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.printf("%10s",boardWithNumbers[i][j]);
				}
				System.out.println();
				System.out.println();
			}
			
			System.out.println("Enter position");
			int firstNumber = scan.nextInt();
			
			//checking position
			while(firstNumber > 16) {
				System.out.println("Enter number in range [1...16]");
				firstNumber = scan.nextInt();
			}
	
			row1=(firstNumber-1) / 4;
			col1=(firstNumber-1) % 4;
			
			//check if you are entered already entered position
			while((boardWithBools[row1][col1]) || (firstNumber > 16)) {
				System.out.println("Enter diffent number");
				firstNumber = scan.nextInt();
				
				//checking position
				while(firstNumber > 16) {
					System.out.println("Enter number in range [1...16]");
					firstNumber = scan.nextInt();
				}
				
				row1=(firstNumber-1) / 4;
				col1=(firstNumber-1) % 4;
			}
			
			//add word from board with words to board for print
			boardWithBools[row1][col1] = true;			
			String temp = board[row1][col1];
			boardForPrint[row1][col1] = temp;
			
			//"clear" console
			loadScreen(50, 60);
			loadScreen(34, 43);
			loadScreen(55, 60);
						
			//print board with numbers and words
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.printf("%10s", boardForPrint[i][j]);
				}
				System.out.println();
				System.out.println();
			}		
			
			System.out.println("Enter second possition");
			int secondNumber = scan.nextInt();
			
			//checking position
			while(secondNumber > 16) {
				System.out.println("Enter number in range [1...16]");
				secondNumber = scan.nextInt();
			}
	
			row2=(secondNumber-1) / 4;
			col2=(secondNumber-1) % 4;
			
			//check if you are entered already entered position
			while(boardWithBools[row2][col2]) {
				System.out.println("Enter diffent number");
				secondNumber = scan.nextInt();
				
				//checking position
				while(secondNumber > 16) {
					System.out.println("Enter number in range [1...16]");
					secondNumber = scan.nextInt();
				}
				
				row2=(secondNumber-1) / 4;
				col2=(secondNumber-1) % 4;
			}
			
			//"clear" console
			loadScreen(50, 60);
			loadScreen(34, 43);
			loadScreen(55, 60);

			temp = board[row2][col2];
			boardForPrint[row2][col2] = temp;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.printf("%10s", boardForPrint[i][j]);
				}
				System.out.println();
				System.out.println();
			}					
			
			//check if words are equal
			if(board[row1][col1].equals(board[row2][col2])) {
				boardWithNumbers[row1][col1] = board[row1][col1];
				boardWithNumbers[row2][col2] = board[row2][col2];
				boardWithBools[row1][col1] = true;
				boardWithBools[row2][col2] = true;
				counter++;
				scoreCounter += 200;
			} else {
				boardForPrint[row1][col1] = boardWithNumbers[row1][col1];
				boardForPrint[row2][col2] = boardWithNumbers[row2][col2];
				boardWithBools[row1][col1] = false;
				boardWithBools[row2][col2] = false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			movesCounter += 2;
			
			//you win
			if(counter == numbers.size()/2) {
				System.out.println();
				System.out.println("CONGRATULATIONS, YOU WON");
				System.out.println("YOUR SCORE IS: " + (scoreCounter - movesCounter));				
			}
			
			System.out.println();			
		}		
	}
}