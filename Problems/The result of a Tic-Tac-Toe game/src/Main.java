import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        /*System.out.println("O X X");
        System.out.println("O X O");
        System.out.println("X O X");*/

        Random generator = new Random();
        int a = generator.nextInt(3);
        int b = generator.nextInt(2) + 1;
        int c = generator.nextInt(4);
        System.out.println(a + " " + b + " " + c);
    }
}