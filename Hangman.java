import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<String> words = loadWords("word.txt");

        if (words.isEmpty()) {
            System.out.println("Word list is empty!");
            return;
        }

        Random random = new Random();
        String wordToGuess = words.get(random.nextInt(words.size())).toLowerCase();

        char[] guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '_');

        int attempts = 6;
        Set<Character> guessedLetters = new HashSet<>();

        System.out.println("Welcome to Hangman Game!");

        while (attempts > 0) {

            System.out.println("\nWord: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attempts);
            System.out.print("Guess a letter: ");

            char guess = input.next().toLowerCase().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter!");
                continue;
            }

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters.add(guess);

            if (wordToGuess.indexOf(guess) >= 0) {

                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guess) {
                        guessedWord[i] = guess;
                    }
                }

                System.out.println("Correct guess!");

            } else {
                attempts--;
                System.out.println("Wrong guess!");
            }

            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
                input.close();
                return;
            }
        }

        System.out.println("\nGame Over! The word was: " + wordToGuess);
        input.close();
    }

    public static List<String> loadWords(String filename) {

        List<String> words = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: words.txt file not found.");
        }

        return words;
    }
}
