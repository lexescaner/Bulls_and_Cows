package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = length();
        int charLength = charLength(length);

        String charCode = charCode(charLength);
        String secretCode = String.valueOf(pseudoRandomNumber(length, charCode));
        /*System.out.println("charCode: " + charCode);
        System.out.println("secretCode: " + secretCode);*/

        char startChar = 0;
        char endChar = 0;
        String startEndChar = "";
        if (charCode.length() != 0) {
            startChar = charCode.charAt(0);
            endChar = charCode.charAt(charCode.length() - 1);
            startEndChar = ", " + startChar + "-" + endChar;
        }
        String mask = mask(length);
        System.out.printf("The secret is prepared: %s (0-9%s).\n", mask, startEndChar);
        System.out.println("Okay, let's start a game!");

        int bull = 0;
        int cow = 0;
        int turn = 1;

        while (bull != length) {
            System.out.printf("Turn %d:\n", turn);
            String guess = scanner.next();
            bull = Integer.parseInt(String.valueOf(grade(guess, secretCode).charAt(0)));
            cow = Integer.parseInt(String.valueOf(grade(guess, secretCode).charAt(1)));
            System.out.println(displayGrade(bull, cow));
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
        scanner.close();
    }

    static int length() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String secretCodeTemp = "";
        int length = 0;
        try {
            secretCodeTemp = scanner.nextLine();
            length = Integer.parseInt(secretCodeTemp);
            //codeLengthCheck(length);
            if (length > 36 || length <= 0) {
                System.out.printf("Error: can't generate a secret number with a length of %d because " +
                        "there aren't enough unique digits.", length);
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", secretCodeTemp);
            System.exit(0);
        }
        return length;
    }

    static int charLength(int length) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of possible symbols in the code:");
        String charLengthTemp = "";
        int charLength = 0;
        try {
            charLengthTemp = scanner.nextLine();
            charLength = Integer.parseInt(charLengthTemp);
            //charLengthCheck(length, charLength);
            if (length > charLength) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", length, charLength);
                System.exit(0);
            } else if(charLength > 36 || charLength <= 0) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", charLengthTemp);
            System.exit(0);
        }
        return charLength;
    }

    static String mask(int length) {
        String temp = "";
        for (int i = 0; i < length; i++) {
            temp += "*";
        }
        return temp;
    }

    static String charCode(int charLength) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String temp = "";
        int tempLength = charLength - 10;
        if (tempLength >= 1) {
            temp = chars.substring(0, tempLength);
        }
        return temp;
    }

    static String pseudoRandomNumber(int length, String charCode) {

        //1. Generate secret code random number
        Random rd = new Random();
        long pseudoRandomNumber = Math.abs(rd.nextLong());
        String temp = String.valueOf(pseudoRandomNumber);

        List<String> listTemp = new ArrayList<>();
        for (int i = temp.length() - 1; i > 0; i--) {
            listTemp.add(String.valueOf(temp.charAt(i)));
        }

        //2. Generate secret code unique values
        List<String> listSecret = new ArrayList<>();
        for (String e : listTemp) {
            if (!listSecret.contains(e)) {
                listSecret.add(e);
            }
        }

        for (int i = 0; i < charCode.length(); i++) {
            listSecret.add(String.valueOf(charCode.charAt(i)));
        }

        //3. Get values from secret list and char code
        String secret = "";
        while (secret.length() < length) {
            String codeTemp = listSecret.get(rd.nextInt(listSecret.size()));
            if (!secret.contains(codeTemp)) {
                secret = codeTemp;
            }
        }
        return secret;
    }

    static String grade(String guess, String secretCode) {
        int bull = 0;
        int cow = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == guess.charAt(i)) {
                bull++;
            } else {
                if (secretCode.indexOf(guess.charAt(i)) >= 0) {
                    cow++;
                }
            }
        }
        return String.valueOf(bull) + String.valueOf(cow);
    }

    static String displayGrade(int bull, int cow) {
        String bullStr = bull == 0 ? "" : bull == 1 ? bull + " bull" : bull + " bulls";
        String andStr = bull > 0 && cow > 0 ? " and " : "";
        String cowStr = cow == 0 ? "" : cow == 1 ? cow + " cow" : cow + " cows";
        String result = cow == 0 && bull == 0 ? "None" : bullStr + andStr + cowStr;
        return String.format("Grade: %s", result);
    }
}
