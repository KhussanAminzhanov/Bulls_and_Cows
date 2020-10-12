package bullscows;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    final static Random random = new Random();

    public static int grader(String number, String guess) {
        int[] grade = getBullsCows(number, guess);
        int bulls = grade[0];
        int cows = grade[1];
        System.out.print("Grade: ");
        if (bulls > 0 && cows > 0) {
            System.out.printf("%d bull(s) and %d cow(s).\n", bulls, cows);
        } else if (bulls > 0) {
            System.out.print(bulls + " bull(s).\n");
        } else if (cows > 0) {
            System.out.print(cows + " cow(s).\n");
        } else {
            System.out.print("None.\n");
        }
        return bulls;
    }

    public static int[] getBullsCows(String number, String guess) {
        int bulls = 0, cows = 0;
        char[] correctDigits = number.toCharArray();
        char[] digits = guess.toCharArray();
        for (int i = 0; i < digits.length; i++) {
           for (int j = 0; j < digits.length; j++) {
               if (digits[j] == correctDigits[i] && j == i) bulls++;
               else if (digits[j] == correctDigits[i]) cows++;
           }
        }
        return new int[]{bulls, cows};
    }

    public static String generate(int length, int numPosSym) {
        char[] symbols = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder str = new StringBuilder();
        int ranNum;
        char c;
        for (int i = 0; i < length; i++) {
            ranNum = random.nextInt(numPosSym);
            c = symbols[ranNum];
            while (str.toString().contains(c + "")) {
                ranNum = random.nextInt(numPosSym);
                c = symbols[ranNum];
            }
            str.append(c);
        }
        return str.toString();
    }

    public static String getRange(int numPosSym) {
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();
        if (numPosSym <= 10) {
            str.append("0-").append(symbols.charAt(numPosSym - 1));
        } else {
            str.append("0-9, ").append("a");
            if (numPosSym > 11) str.append("-").append(symbols.charAt(numPosSym - 1));
        }
        return str.toString();
    }

    public static boolean checkGuess(String guess, int numPosSym) {
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        for (char c : guess.toCharArray()) {
            if (symbols.indexOf(c) > numPosSym - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Input the length of the secret code: ");
        int length, numPosSym;
        try {
            length = scanner.nextInt();
            if (length > 36 || length <= 0) {
                System.out.println("error: incorrect length");
                return;
            }

            System.out.println("Input the number of possible symbols in the code: ");
            numPosSym = scanner.nextInt();
            scanner.nextLine();
            if (numPosSym < length || numPosSym > 36) {
                System.out.println("error: incorrect number of possible symbols");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("error: incorrect input");
            return;
        }

        String number = generate(length, numPosSym);
        System.out.printf("The secret is prepared: %s (%s).\n",
                "*".repeat(length), getRange(numPosSym));
        String guess;
        int bulls = 0;
        int i = 1;
        while (bulls != length) {
            System.out.println("Turn " + i);
            guess = scanner.nextLine().toLowerCase();
            if (guess.length() != length || checkGuess(guess, numPosSym)) {
                System.out.println("error: answer contains invalid symbols");
                return;
            }
            bulls = grader(number, guess);
            i++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}